package culinart.api.usuario;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.domain.usuario.dto.mapper.UsuarioMapper;
import culinart.domain.usuarioPreferencia.dto.UsuarioPreferenciaExibicaoDTO;
import culinart.domain.usuarioPreferencia.dto.mapper.UsuarioPreferenciaMapper;
import culinart.service.usuario.UsuarioService;
import culinart.service.usuario.autenticacao.dto.UsuarioLoginDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import culinart.service.usuario.preferencia.UsuarioPreferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private final UsuarioService usuarioService;


    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioExibicaoDTO>> listarTodosUsuarios() {
        if (usuarioService.buscaNaoPossuiResultado()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarioService.listarTodosOsUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> listarUsuarioPorID(@PathVariable int id) {
        if (usuarioService.usuarioIsEmpty(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioService.listarUsuarioPorId(id));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioExibicaoDTO> cadastrarUsuario(@RequestBody UsuarioCriacaoDTO usuarioCriacao) {
        return ResponseEntity.status(201).body(usuarioService.cadastrarUsuario(usuarioCriacao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        if (usuarioService.usuarioIsEmpty(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioService.atualizarUsuario(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DesativarUsuario(@PathVariable int id) {
        if (usuarioService.usuarioIsEmpty(id)) {
            return ResponseEntity.status(404).build();
        }
        usuarioService.desativarUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        UsuarioTokenDTO usuarioTokenDTO = this.usuarioService.autenticar(usuarioLoginDTO);
        return ResponseEntity.ok(usuarioTokenDTO);
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<UsuarioExibicaoDTO>> exibirUsuariosAtivos(){
        if (usuarioService.exibirUsuariosAtivos().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(usuarioService.exibirUsuariosAtivos());
    }
    @GetMapping("/inativos")
    public ResponseEntity<List<UsuarioExibicaoDTO>> exibirUsuariosInativos(){
        if (usuarioService.exibirUsuariosInativos().isEmpty()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.ok(usuarioService.exibirUsuariosInativos());
    }

    @PatchMapping("/senhas/{idUsuario}")
    public ResponseEntity<UsuarioExibicaoDTO> atualizarSenhaUsuario(@PathVariable int idUsuario, String senha){
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioService.atualizarSenhaUsuario(idUsuario,senha)));
    }

    @PutMapping("/permissionar/administrador/{idUsuario}")
    public ResponseEntity<UsuarioExibicaoDTO> permissionarUsuarioAdministrador(int idUsuario){
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioService.permissionarUsuarioAdministrador(idUsuario)));
    }

    @PutMapping("/permissionar/cliente/{idUsuario}")
    public ResponseEntity<UsuarioExibicaoDTO> permissionarUsuarioCliente(int idUsuario){
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioService.permissionarUsuarioCliente(idUsuario)));
    }

    @PutMapping("/permissionar/funcionario/{idUsuario}")
    public ResponseEntity<UsuarioExibicaoDTO> permissionarUsuarioFuncionario(int idUsuario){
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioService.permissionarUsuarioFuncionario(idUsuario)));
    }

    @PutMapping("/ativar/{idUsuario}")
    public ResponseEntity<UsuarioExibicaoDTO> ativarUsuario(@PathVariable int idUsuario){
        return ResponseEntity.ok(UsuarioMapper.toDTO(usuarioService.ativarUsuario(idUsuario)));
    }
}
