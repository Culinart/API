package culinart.service.receita;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.avaliacao.repository.AvaliacaoRepository;
import culinart.domain.categoria.Categoria;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.ingrediente.repository.IngredienteRepository;
import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.modoPreparo.repository.ModoPreparoRepository;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaCadastroDTO;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
import culinart.domain.receitaPreferencia.ReceitaPreferencia;
import culinart.domain.receitaPreferencia.repository.ReceitaPreferenciaRepository;
import culinart.service.receita.ingrediente.IngredienteService;
import culinart.service.receita.modoPreparo.ModoPreparoService;
import culinart.service.receita.receitaCategoria.ReceitaCategoriaService;
import culinart.service.receita.receitaPreferencia.ReceitaPreferenciaService;
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
    private final ReceitaPreferenciaService receitaPreferenciaService;
    private final ReceitaPreferenciaRepository receitaPreferenciaRepository;

    public List<Receita> exibirTodasReceitas() {
        return receitaRepository.findAll();
    }

    public Receita exibirReceitaPorId(int id) {
        return receitaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));
    }

    public Receita cadastrarReceita(ReceitaCadastroDTO receitaCadastroDTO) {
        if (receitaRepository.existsByNome(receitaCadastroDTO.getNome())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita já cadastrada");
        }

        Receita receita = receitaRepository.saveAndFlush(ReceitaMapper.toEntity(receitaCadastroDTO));

        List<Ingrediente> ingredientes = receitaCadastroDTO.getIngredientes();
        List<ModoPreparo> modoPreparos = receitaCadastroDTO.getModoPreparos();
        List<Categoria> categorias = receitaCadastroDTO.getCategorias();
        List<Preferencia> preferencias = receitaCadastroDTO.getPreferencias();

        List<ReceitaCategoria> receitaCategorias = new ArrayList<>();
        for (Categoria categoria : categorias) {
            ReceitaCategoria receitaCategoria = new ReceitaCategoria();
            receitaCategoria.setCategoria(categoria);
            receitaCategoria.setReceita(receita);
            receitaCategorias.add(receitaCategoria);
        }

        List<ReceitaPreferencia> receitaPreferencias = new ArrayList<>();
        for (Preferencia preferencia : preferencias) {
            ReceitaPreferencia receitaPreferencia = new ReceitaPreferencia();
            receitaPreferencia.setPreferencia(preferencia);
            receitaPreferencia.setReceita(receita);
            receitaPreferencias.add(receitaPreferencia);
        }

        for (Ingrediente ingrediente : ingredientes) {
            ingrediente.setReceita(receita);

        }

        for (ModoPreparo modoPreparo : modoPreparos) {
            modoPreparo.setReceita(receita);
        }

        receitaCategoriaService.saveAll(receitaCategorias);
        receitaPreferenciaService.saveAll(receitaPreferencias);
        this.ingredienteService.saveAll(ingredientes);
        this.modoPreparoService.saveAll(modoPreparos);

        Receita receitaSalva = receitaRepository.findById(receita.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

        return receitaSalva;
    }

    public Receita atualizarReceita(int id, ReceitaCadastroDTO receitaCadastroDTO) {
        Receita receitaAntiga = receitaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

        List<ReceitaCategoria> receitasCategoriasAntigas = receitaCategoriaRepository.findByReceita_Id(receitaAntiga.getId());

        List<ReceitaCategoria> listCategoriasNovas = new ArrayList<>();
        List<ReceitaCategoria> listCategoriasRemover = new ArrayList<>();

        for (ReceitaCategoria categoriaAntigas : receitasCategoriasAntigas) {
            Boolean existe = false;
            for (Categoria categoria : receitaCadastroDTO.getCategorias()) {
                if (categoriaAntigas.getCategoria().getId() == categoria.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                listCategoriasRemover.add(categoriaAntigas);
            }
        }

        for (Categoria categoria : receitaCadastroDTO.getCategorias()) {
            Boolean existe = false;
            for (ReceitaCategoria categoriaAntigas : receitasCategoriasAntigas) {
                if (categoriaAntigas.getCategoria().getId() == categoria.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                ReceitaCategoria receitaCategoria = new ReceitaCategoria();
                receitaCategoria.setCategoria(categoria);
                receitaCategoria.setReceita(receitaAntiga);
                listCategoriasNovas.add(receitaCategoria);
            }
        }

        receitaCategoriaRepository.saveAll(listCategoriasNovas);
        receitaCategoriaRepository.deleteAll(listCategoriasRemover);

        List<ReceitaPreferencia> receitasPreferenciasAntigas = receitaPreferenciaRepository.findByReceita_Id(receitaAntiga.getId());

        List<ReceitaPreferencia> listPreferenciasNovas = new ArrayList<>();
        List<ReceitaPreferencia> listPreferenciasRemover = new ArrayList<>();

        for (ReceitaPreferencia preferenciaAntigas : receitasPreferenciasAntigas) {
            Boolean existe = false;
            for (Preferencia preferencia : receitaCadastroDTO.getPreferencias()) {
                if (preferenciaAntigas.getPreferencia().getId() == preferencia.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                listPreferenciasRemover.add(preferenciaAntigas);
            }
        }

        for (Preferencia preferencia : receitaCadastroDTO.getPreferencias()) {
            Boolean existe = false;
            for (ReceitaPreferencia preferenciaAntigas : receitasPreferenciasAntigas) {
                if (preferenciaAntigas.getPreferencia().getId() == preferencia.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                ReceitaPreferencia receitaPreferencia = new ReceitaPreferencia();
                receitaPreferencia.setPreferencia(preferencia);
                receitaPreferencia.setReceita(receitaAntiga);
                listPreferenciasNovas.add(receitaPreferencia);
            }
        }

        receitaPreferenciaRepository.saveAll(listPreferenciasNovas);
        receitaPreferenciaRepository.deleteAll(listPreferenciasRemover);

        Receita novaReceita = ReceitaMapper.toEntity(receitaCadastroDTO);
        novaReceita.setId(receitaAntiga.getId());
        novaReceita.setReceitaCategorias(receitaCategoriaRepository.findByReceita_Id(receitaAntiga.getId()));

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

        return receitaRepository.save(novaReceita);
    }

    public void deletarReceita(int id) {
        if (receitaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada");
        }

        List<ReceitaCategoria> receitaCategorias = receitaCategoriaRepository.findByReceita_Id(id);
        List<Ingrediente> ingredientes = ingredienteRepository.findByReceita_Id(id);
        List<ModoPreparo> modoPreparos = modoPreparoRepository.findByReceita_Id(id);
        List<Avaliacao> avaliacoes = avaliacaoRepository.findByReceita_Id(id);
        List<ReceitaPreferencia> receitaPreferencias = receitaPreferenciaRepository.findByReceita_Id(id);


        receitaCategoriaRepository.deleteAll(receitaCategorias);
        ingredienteRepository.deleteAll(ingredientes);
        modoPreparoRepository.deleteAll(modoPreparos);
        avaliacaoRepository.deleteAll(avaliacoes);
        receitaPreferenciaRepository.deleteAll(receitaPreferencias);

        receitaRepository.deleteById(id);
    }
}
