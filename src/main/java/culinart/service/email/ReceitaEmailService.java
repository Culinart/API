package culinart.service.email;

import culinart.domain.email.ReceitaEmail;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.receita.repository.ReceitaRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.utils.enums.StatusAtivoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReceitaEmailService {


    private final EnviadorEmailService enviadorEmailService;
    private final UsuarioRepository usuarioRepository;
    private final ReceitaRepository receitaRepository;
    private final String emailEmpresa = "culinart.oficial@gmail.com";

    private List<ReceitaEmail> receitaEmails = new ArrayList<>();

    public void criar(ReceitaEmail receitaEmail) {
        this.receitaEmails.add(receitaEmail);
    }

    public List<ReceitaEmail> listar() {
        return receitaEmails;
    }

    public ReceitaEmail buscarPorId(UUID id) {
        return buscarPorIndice(id);
    }

    private ReceitaEmail buscarPorIndice(UUID id) {
        return this.receitaEmails.stream()
                .filter(receitaEmail -> receitaEmail.getId().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("ReceitaEmail não encontrada!")
                );
    }

    public String publicarReceita(int idReceita) {
        Receita receita = receitaRepository.findById(idReceita).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada para envio de email"));

        List<Usuario> usuariosAtivos = usuarioRepository.findByIsAtivoEquals(StatusAtivoEnum.ATIVO);

        usuariosAtivos.forEach(usuario -> usuario.receberReceita(enviadorEmailService, ReceitaMapper.toEmailDTO(receita), emailEmpresa));
        return "Receita enviada no email com sucesso";
    }
}
