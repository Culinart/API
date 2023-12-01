package culinart.service.receita.receitaCategoria;

import culinart.api.receita.ReceitaController;
import culinart.api.receita.ingrediente.IngredienteController;
import culinart.api.receita.modoPreparo.ModoPreparoController;
import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.dto.CategoriaCadastroDTO;
import culinart.domain.categoria.repository.CategoriaRepository;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.ingrediente.repository.IngredienteRepository;
import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.preferencia.dto.PreferenciaCadastroDTO;
import culinart.domain.preferencia.repository.PreferenciaRepository;
import culinart.domain.receita.Receita;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaCadastroDTO;
import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
import culinart.service.receita.ReceitaService;
import culinart.service.receita.ingrediente.IngredienteService;
import culinart.service.receita.modoPreparo.ModoPreparoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaCategoriaService {

    private final ReceitaCategoriaRepository repository;
    private final IngredienteController ingredienteController;
    private final IngredienteRepository ingredienteRepository;
    private final IngredienteService ingredienteService;
    private final ModoPreparoController modoPreparoController;
    private final ModoPreparoService modoPreparoService;
    private final PreferenciaRepository preferenciaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ReceitaController receitaController;
    private final ReceitaService receitaService;

    public List<ReceitaCategoria> exibirTodasReceitasCategorias() {
        return repository.findAll();
    }

    public List<ReceitaCategoria> exibirTodasReceitasCategoriasComFavoritos(int idUsuario) {
        return repository.findReceitaCategoriaOrderedByFavoritos(idUsuario);
    }

    public ReceitaCategoria cadastrarReceitaCategoria(ReceitaCategoriaCadastroDTO receitaCategoria) {
        List<Ingrediente> ingredientes = receitaCategoria.getReceita().getIngredientes();
        List<ModoPreparo> modoPreparos = receitaCategoria.getReceita().getModoPreparos();
        List<CategoriaCadastroDTO> categorias = receitaCategoria.getCategoria();
        List<PreferenciaCadastroDTO> preferencias = receitaCategoria.getPreferencia();

        Receita receita = receitaCategoria.getReceita();
        receita = receitaService.cadastrarReceita(receita);

        for (Ingrediente ingrediente : ingredientes) {
            ingrediente.setReceita(receita);
        }

        for (ModoPreparo modoPreparo : modoPreparos) {
            modoPreparo.setReceita(receita);
        }

        // Em seguida, cadastre a receita

        List<Categoria> categoriaList = new ArrayList<>();
        for (CategoriaCadastroDTO categoria : categorias) {
            Categoria categoriaEntity = categoriaRepository.findById(categoria.getId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!"));
            categoriaList.add(categoriaEntity);
        }

        List<Preferencia> preferenciaList = new ArrayList<>();
        for (PreferenciaCadastroDTO preferenciaCadastroDTO : preferencias) {
            Preferencia preferencia = preferenciaRepository.findById(preferenciaCadastroDTO.getId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada!"));
            preferenciaList.add(preferencia);
        }

        ReceitaCategoria entity = new ReceitaCategoria();
        entity.setReceita(receitaCategoria.getReceita());
        entity.setCategoria(categoriaList);
        entity.setPreferencia(preferenciaList);
        this.ingredienteService.saveAll(ingredientes);
        this.modoPreparoService.saveAll(modoPreparos);
        return repository.save(entity);
    }


    public void deletarReceitaCategoria(int idReceitaCategoria) {
        ReceitaCategoria receitaCategoria = repository.findById(idReceitaCategoria).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "ReceitaCategoria não encontrada"));
        repository.delete(receitaCategoria);
    }

    public List<ReceitaCategoria> pesquisarReceitaCategoria(String parametro) {
        return repository.findByParametro(parametro);
    }

    public byte[] getImagem(int id) {
        ReceitaCategoria receita = repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada"));

        return receita.getReceita().getImagem();
    }
}

