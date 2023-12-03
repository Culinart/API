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
import culinart.domain.receita.imagem.ImagemReceita;
import culinart.domain.receita.imagem.repository.ImagemReceitaRepository;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
import culinart.domain.receitaPreferencia.ReceitaPreferencia;
import culinart.domain.receitaPreferencia.repository.ReceitaPreferenciaRepository;
import culinart.service.receita.ingrediente.IngredienteService;
import culinart.service.receita.modoPreparo.ModoPreparoService;
import culinart.service.receita.receitaCategoria.ReceitaCategoriaService;
import culinart.service.receita.receitaPreferencia.ReceitaPreferenciaService;
import culinart.utils.ImageCompressionUtil;
import culinart.utils.ReceitaSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

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
    private final ImagemReceitaRepository imagemReceitaRepository;

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

        List<Ingrediente> ingredientes = ReceitaMapper.toIngredienteEntity(receitaCadastroDTO.getIngredientes());
        List<ModoPreparo> modoPreparos = ReceitaMapper.toEntity(receitaCadastroDTO.getModoPreparos());
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

        List<Ingrediente> ingredientesAntigos = ingredienteRepository.findByReceita_Id(id);
        List<Ingrediente> ingredientesNovos = new ArrayList<>();
        List<Ingrediente> ingredientesRemover = new ArrayList<>();

        for (Ingrediente ingredienteAntigo : ingredientesAntigos) {
            Boolean existe = false;
            for (Ingrediente ingrediente : ReceitaMapper.toIngredienteEntity(receitaCadastroDTO.getIngredientes())) {
                if (ingredienteAntigo.getId() == ingrediente.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                ingredientesRemover.add(ingredienteAntigo);
            }
        }

        for (Ingrediente ingrediente : ReceitaMapper.toIngredienteEntity(receitaCadastroDTO.getIngredientes())) {
            Boolean existe = false;
            for (Ingrediente ingredienteAntigo : ingredientesAntigos) {
                if (ingredienteAntigo.getId() == ingrediente.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                ingrediente.setReceita(receitaAntiga);
                ingredientesNovos.add(ingrediente);
            }
        }

        ingredienteRepository.saveAll(ingredientesNovos);
        ingredienteRepository.deleteAll(ingredientesRemover);


        List<ModoPreparo> modoPreparosAntigos = modoPreparoRepository.findByReceita_Id(id);

List<ModoPreparo> modoPreparosNovos = new ArrayList<>();
        List<ModoPreparo> modoPreparosRemover = new ArrayList<>();

        for (ModoPreparo modoPreparoAntigo : modoPreparosAntigos) {
            Boolean existe = false;
            for (ModoPreparo modoPreparo : ReceitaMapper.toEntity(receitaCadastroDTO.getModoPreparos())) {
                if (modoPreparoAntigo.getId() == modoPreparo.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                modoPreparosRemover.add(modoPreparoAntigo);
            }
        }

        for (ModoPreparo modoPreparo : ReceitaMapper.toEntity(receitaCadastroDTO.getModoPreparos())) {
            Boolean existe = false;
            for (ModoPreparo modoPreparoAntigo : modoPreparosAntigos) {
                if (modoPreparoAntigo.getId() == modoPreparo.getId()) {
                    existe = true;
                }
            }
            if (!existe) {
                modoPreparo.setReceita(receitaAntiga);
                modoPreparosNovos.add(modoPreparo);
            }
        }

        List<Avaliacao> avaliacoesAntigos = avaliacaoRepository.findByReceita_Id(id);

        Receita novaReceita = ReceitaMapper.toEntity(receitaCadastroDTO);
        novaReceita.setId(receitaAntiga.getId());
        novaReceita.setReceitaCategorias(receitaCategoriaRepository.findByReceita_Id(receitaAntiga.getId()));
        novaReceita.setReceitaPreferencias(receitaPreferenciaRepository.findByReceita_Id(receitaAntiga.getId()));
        novaReceita.setIngredientes(ingredienteRepository.findByReceita_Id(receitaAntiga.getId()));
        novaReceita.setModoPreparos(modoPreparoRepository.findByReceita_Id(receitaAntiga.getId()));
        novaReceita.setAvaliacoes(avaliacaoRepository.findByReceita_Id(receitaAntiga.getId()));

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

    public List<Receita> pesquisarReceitas(String termo) {
        return receitaRepository.findAll(ReceitaSpecifications.porNomeOuIngredienteOuCategoria(termo));
    }

    public String adcionarImagemReceita(MultipartFile imagem, int idReceita) throws IOException {

        Receita receita = receitaRepository.findById(idReceita).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrado"));

         imagemReceitaRepository.save(ImagemReceita
                .builder()
                .nome(imagem.getOriginalFilename())
                .foto(ImageCompressionUtil.compress(imagem.getBytes()))
                .receita(receita)
                .build());

        if(imagem!=null){
            return "upload com sucesso : "+ imagem.getOriginalFilename();
        }
       return null;
    }

    public byte[] visualizarImagemReceita(Integer idReceita) throws DataFormatException {

        ImagemReceita foto = imagemReceitaRepository.findByReceita_Id(idReceita).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Imagem não encontrada"));

        return ImageCompressionUtil.decompress(foto.getFoto());
    }
}