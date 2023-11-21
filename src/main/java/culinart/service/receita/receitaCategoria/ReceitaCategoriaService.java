package culinart.service.receita.receitaCategoria;

import culinart.api.receita.ReceitaController;
import culinart.api.receita.categoria.CategoriaController;
import culinart.api.receita.ingrediente.IngredienteController;
import culinart.api.receita.modoPreparo.ModoPreparoController;
import culinart.api.receita.preferencia.PreferenciaController;
import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.dto.CategoriaCadastroDTO;
import culinart.domain.categoria.repository.CategoriaRepository;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.preferencia.dto.PreferenciaCadastroDTO;
import culinart.domain.preferencia.repository.PreferenciaRepository;
import culinart.domain.receita.Receita;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaCadastroDTO;
import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
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
    private final ModoPreparoController modoPreparoController;
    private final PreferenciaRepository preferenciaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ReceitaController receitaController;

    public List<ReceitaCategoria> exibirTodasReceitasCategorias() {
        return repository.findAll();
    }

    public ReceitaCategoria cadastrarReceitaCategoria(ReceitaCategoriaCadastroDTO receitaCategoria) {
        List<Ingrediente> ingredientes = receitaCategoria.getReceita().getIngredientes();
        List<ModoPreparo> modoPreparos = receitaCategoria.getReceita().getModoPreparos();
        List<CategoriaCadastroDTO> categorias = receitaCategoria.getCategoria();
        List<PreferenciaCadastroDTO> preferencias = receitaCategoria.getPreferencia();
        Receita receita = receitaCategoria.getReceita();

        ingredientes.forEach(ingredienteController::cadastarIngrediente);
        modoPreparos.forEach(modoPreparoController::cadastrarModoPreparo);

        List<Categoria> categoriaList = new ArrayList<>();
        for (CategoriaCadastroDTO categoria : categorias) {
            Categoria categoriaEntity = categoriaRepository.findById(categoria.getId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada!"));
            categoriaList.add(categoriaEntity);
        }

        List<Preferencia> preferenciaList = new ArrayList<>();
        for (PreferenciaCadastroDTO preferenciaCadastroDTO : preferencias){
            preferenciaRepository.findById(preferenciaCadastroDTO.getId()).orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Preferencia não encontrada!"));
        }

        receitaController.cadastrarReceita(receita);

        ReceitaCategoria entity = new ReceitaCategoria();
        entity.setReceita(receitaCategoria.getReceita());
        entity.setCategoria(categoriaList);
        entity.setPreferencia(preferenciaList);
        return repository.save(entity);
    }

    public void deletarReceitaCategoria(int idReceitaCategoria){
        ReceitaCategoria receitaCategoria = repository.findById(idReceitaCategoria).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "ReceitaCategoria não encontrada"));
        repository.delete(receitaCategoria);
    }
}
