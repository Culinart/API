package culinart.service.usuario;

import culinart.api.usuario.configuration.security.jwt.GerenciadorTokenJwt;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.domain.usuario.dto.mapper.UsuarioMapper;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.service.usuario.autenticacao.dto.UsuarioLoginDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;
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
        if (this.usuarioRepository.existsByEmail(usuario.getEmail())) {
            System.out.println("Entrou aqui");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuario já cadastrado");
        }
        Usuario novoUsuario = UsuarioMapper.toEntity(usuario);

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        novoUsuario.setSenha(senhaCriptografada);
        return UsuarioMapper.toDTO(usuarioRepository.save(novoUsuario));
    }

    public List<UsuarioExibicaoDTO> listarTodosOsUsuarios() {
        List<UsuarioExibicaoDTO> lista = new ArrayList<>();
        for (Usuario usuario : usuarioRepository.findAll()) {
            lista.add(UsuarioMapper.toDTO(usuario));
        }
        return lista;
    }

    public UsuarioExibicaoDTO listarUsuarioPorId(int id) {
        return UsuarioMapper.toDTO(usuarioRepository.findById(id).get());
    }


    public UsuarioExibicaoDTO atualizarUsuario(int id, Usuario usuario) {
        usuario.setId(id);
        Optional<Usuario> usuarioAnterior = usuarioRepository.findById(id);
        if(usuarioAnterior.isEmpty()){
            throw new IllegalArgumentException("Usuario não existe");
        }
        return UsuarioMapper.toDTO(usuarioRepository.save(usuario));
    }

    public void desativarUsuario(int id) {
        Usuario usuarioDesativado = usuarioRepository.findById(id).get();
        usuarioDesativado.setIsAtivo(0);
        usuarioRepository.save(usuarioDesativado);
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

        return UsuarioMapper.toDTO(usuarioAutenticado,token);
    }

    public List<UsuarioExibicaoDTO> exibirUsuariosAtivos() {
        List<UsuarioExibicaoDTO> lista = new ArrayList<>();
        for (Usuario usuario : usuarioRepository.findByIsAtivoEquals(1)) { //TODO: Mudar para enum lá na frente
            lista.add(UsuarioMapper.toDTO(usuario));
        }
        return lista;
    }

    public List<UsuarioExibicaoDTO> exibirUsuariosInativos() {
        List<UsuarioExibicaoDTO> lista = new ArrayList<>();
        for (Usuario usuario : usuarioRepository.findByIsAtivoEquals(0)) { //TODO: Mudar para enum lá na frente
            lista.add(UsuarioMapper.toDTO(usuario));
        }
        return lista;
    }

    public Usuario ativarUsuario(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );

        usuario.setIsAtivo(StatusAtivoEnum.ATIVO.getCodigo());
        return usuario;
    }

    public Usuario permissionarUsuarioAdministrador(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );
        usuario.setPermissao(PermissaoEnum.ADMINISTRADOR);
        return usuario;
    }

    public Usuario permissionarUsuarioCliente(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );
        usuario.setPermissao(PermissaoEnum.CLIENTE);
        return usuario;
    }

    public Usuario permissionarUsuarioFuncionario(int idUsuario) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );
        usuario.setPermissao(PermissaoEnum.FUNCIONARIO);
        return usuario;
    }

    public Usuario atualizarSenhaUsuario(int idUsuario, String senha) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não cadastrado")
                );

        usuario.setSenha(senha);
        return usuario;
    }
}
