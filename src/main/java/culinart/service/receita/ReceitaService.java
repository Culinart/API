package culinart.service.receita;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.avaliacao.repository.AvaliacaoRepository;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.ingrediente.repository.IngredienteRepository;
import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.modoPreparo.repository.ModoPreparoRepository;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaCadastroDTO;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
import culinart.service.receita.ingrediente.IngredienteService;
import culinart.service.receita.modoPreparo.ModoPreparoService;
import culinart.service.receita.receitaCategoria.ReceitaCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReceitaService {
    private final ReceitaRepository receitaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final ModoPreparoRepository modoPreparoRepository;
    private final AvaliacaoRepository avaliacaoRepository;
    private final ReceitaCategoriaRepository receitaCategoriaRepository;
    private final ReceitaCategoriaService receitaCategoriaService;
    private final IngredienteService ingredienteService;
    private final ModoPreparoService modoPreparoService;

    public List<Receita> exibirTodasReceitas() {
        List<Receita> listReceita = new ArrayList<>();
        for (Receita receita : receitaRepository.findAll()) {
            List<Ingrediente> allIngredientes = ingredienteRepository.findByReceita_Id(receita.getId());
            List<ModoPreparo> allModosPreparos = modoPreparoRepository.findByReceita_Id(receita.getId());
            List<Avaliacao> allAvaliacoes = avaliacaoRepository.findByReceita_Id(receita.getId());
            List<ReceitaCategoria> allReceitaCategoria = receitaCategoriaRepository.findByReceita_Id(receita.getId());
            if (allIngredientes.isEmpty()) {
                break;
            }
            receita.setIngredientes(allIngredientes);
            receita.setModoPreparos(allModosPreparos);
            receita.setAvaliacoes(allAvaliacoes);
            receita.setReceitaCategorias(allReceitaCategoria);
            listReceita.add(receita);
        }

        return listReceita;
    }

    public Receita exibirReceitaPorId(int id) {
        return receitaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));
    }

    public Receita cadastrarReceita(ReceitaCadastroDTO receitaCadastroDTO) {
        if (receitaRepository.existsByNome(receitaCadastroDTO.getNome())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita já cadastrada");
        }

        if (receitaCadastroDTO.getAvaliacoes() == null) {
            receitaCadastroDTO.setAvaliacoes(new ArrayList<>());
        }

        Receita receita = receitaRepository.saveAndFlush(ReceitaMapper.toEntity(receitaCadastroDTO));

        List<Ingrediente> ingredientes = receitaCadastroDTO.getIngredientes();
        List<ModoPreparo> modoPreparos = receitaCadastroDTO.getModoPreparos();


        for (Ingrediente ingrediente : ingredientes) {
            ingrediente.setReceita(receita);
        }

        for (ModoPreparo modoPreparo : modoPreparos) {
            modoPreparo.setReceita(receita);
        }

        this.ingredienteService.saveAll(ingredientes);
        this.modoPreparoService.saveAll(modoPreparos);

        List<ReceitaCategoria> receitasCategorias = new ArrayList<>();
        for (ReceitaCategoria receitaCategoria : receitaCadastroDTO.getReceitaCategorias()) {
            receitaCategoria.setReceita(receita);
            receitaCategoria.setCategoria(receitaCategoria.getCategoria());
            receitasCategorias.add(receitaCategoria);
        }

        receitaCategoriaService.saveAll(receitasCategorias);

        return receita;
    }

    public Receita atualizarReceita(int id, ReceitaCadastroDTO receitaCadastroDTO) {
        if (receitaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }
        Receita receitaAntiga = receitaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

        List<ReceitaCategoria> receitasCategoriasAntigas = receitaCategoriaRepository.findByReceita_Id(receitaAntiga.getId());

        List<ReceitaCategoria> listCategoria = new ArrayList<>();
        for (ReceitaCategoria categoriasAntigas : receitasCategoriasAntigas) {
            for (ReceitaCategoria dtoReceitaCategoria : receitaCadastroDTO.getReceitaCategorias()) {
                categoriasAntigas.setCategoria(dtoReceitaCategoria.getCategoria());
                listCategoria.add(categoriasAntigas);
            }
        }
        receitaCategoriaRepository.saveAll(listCategoria);

        Receita novaReceita = ReceitaMapper.toEntity(receitaCadastroDTO);
        novaReceita.setId(receitaAntiga.getId());

        if (receitaCadastroDTO.getIngredientes() != null) {
            novaReceita.setIngredientes(receitaCadastroDTO.getIngredientes());
        } else {
            novaReceita.setIngredientes(receitaAntiga.getIngredientes());
        }

        if (receitaCadastroDTO.getModoPreparos() != null) {
            novaReceita.setModoPreparos(receitaCadastroDTO.getModoPreparos());
        } else {
            novaReceita.setModoPreparos(receitaAntiga.getModoPreparos());
        }

        List<ReceitaCategoria> receitasCategorias = new ArrayList<>();
        for (ReceitaCategoria receitaCategoria : receitaCadastroDTO.getReceitaCategorias()) {
            receitaCategoria.setReceita(novaReceita);
            receitaCategoria.setCategoria(receitaCategoria.getCategoria());
            receitasCategorias.add(receitaCategoria);
        }
        receitaCategoriaService.saveAll(receitasCategorias);

        return receitaRepository.save(novaReceita);
    }

    public void deletarReceita(int id) {
        if (receitaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }

        receitaRepository.delete(receitaRepository.findById(id).get());
    }
}
