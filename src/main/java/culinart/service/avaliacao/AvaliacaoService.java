package culinart.service.avaliacao;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.avaliacao.dto.AvaliacaoCadastroDTO;
import culinart.domain.avaliacao.mapper.AvaliacaoMapper;
import culinart.domain.avaliacao.repository.AvaliacaoRepository;
import culinart.domain.receita.Receita;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.utils.PilhaObj;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvaliacaoService {
    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ReceitaRepository receitaRepository;
    private final PilhaObj pilha = new PilhaObj();
    public List<Avaliacao> exibirTodasAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao exibirAvaliacaoPorId(int id) {
        return avaliacaoRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Avaliação não encontrada"));
    }

    public Avaliacao cadastrarAvaliacao(AvaliacaoCadastroDTO avaliacaoCadastroDTO) {

        Usuario usuario = usuarioRepository.findById(avaliacaoCadastroDTO.getIdUsuario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado"));

        Receita receita = receitaRepository.findById(avaliacaoCadastroDTO.getIdReceita())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada"));

        Avaliacao entity = AvaliacaoMapper.toEntity(avaliacaoCadastroDTO, usuario, receita);
        avaliacaoRepository.save(entity);
        receita.getAvaliacoes().add(entity);
        receitaRepository.save(receita);
        pilha.push(entity.getId());
        return entity;
    }

    public void desfazer() {
        int id = pilha.pop(); // Remove e obtém o ID da pilha
        if (id != -1) {
            avaliacaoRepository.deleteById(id); // Remove a avaliação do banco de dados
        } else {
            throw new IllegalArgumentException(String.valueOf(HttpStatus.BAD_REQUEST));
        }
    }
}
