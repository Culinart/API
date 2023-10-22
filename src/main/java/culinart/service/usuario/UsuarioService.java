package culinart.service.usuario;

import culinart.api.usuario.configuration.security.jwt.GerenciadorTokenJwt;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.domain.usuario.mapper.UsuarioMapper;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.service.usuario.autenticacao.dto.UsuarioLoginDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GerenciadorTokenJwt gerenciadorTokenJwt;

    @Autowired
    private AuthenticationManager authenticationManager;


    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioExibicaoDTO cadastrarUsuario(UsuarioCriacaoDTO usuario) {
        if (buscarUsuarioPorBuscaBinaria(usuario.getEmail())) {
            throw new IllegalArgumentException("Usuario já cadastrado");
        }
        Usuario novoUsuario = UsuarioMapper.of(usuario);

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);

        return UsuarioMapper.of(usuarioRepository.save(novoUsuario));
    }

    public List<UsuarioExibicaoDTO> listarTodosOsUsuarios() {
        List<UsuarioExibicaoDTO> lista = new ArrayList<>();
        for (Usuario usuario : usuarioRepository.findAll()) {
            lista.add(UsuarioMapper.of(usuario));
        }
        return lista;
    }

    public UsuarioExibicaoDTO listarUsuarioPorId(int id) {
        return UsuarioMapper.of(usuarioRepository.findById(id).get());
    }


    public UsuarioExibicaoDTO atualizarUsuario(int id, Usuario usuario) {
        usuario.setId(id);
        return UsuarioMapper.of(usuarioRepository.save(usuario));
    }

    public void desativarUsuario(int id) {
        Usuario usuarioDesativado = usuarioRepository.findById(id).get();
        usuarioDesativado.setIsAtivo(0);
        usuarioRepository.save(usuarioDesativado);
    }

    public Boolean buscarUsuarioPorBuscaBinaria(String email) {
        List<Usuario> vetor = usuarioRepository.findAll();
        for (int i = 0; i < vetor.toArray().length; i++) {
            if (vetor.get(i).getEmail().equals(email)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

    public Boolean buscaNaoPossuiResultado() {
        return usuarioRepository.findAll().isEmpty();
    }

    public Boolean usuarioIsEmpty(int id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.isEmpty();
    }

    //Autenticação de Usuario

    public UsuarioTokenDTO autenticar(UsuarioLoginDTO usuarioLoginDTO){
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDTO.getEmail(),usuarioLoginDTO.getSenha()
        );

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmail(usuarioLoginDTO.getEmail())
                        .orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não cadastrado")
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado,token);
    }
}
