package culinart.api.usuario;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.integration.ViaCep.ViaCepIntegrationService;
import culinart.integration.ViaCep.dto.ViaCepResponse;
import culinart.service.usuario.UsuarioService;
import culinart.service.usuario.autenticacao.dto.UsuarioLoginDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final ViaCepIntegrationService viaCepIntegrationService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, ViaCepIntegrationService viaCepIntegrationService) {
        this.usuarioService = usuarioService;
        this.viaCepIntegrationService = viaCepIntegrationService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioExibicaoDTO>> listarTodosUsuarios() {
        if (usuarioService.buscaNaoPossuiResultado()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarioService.listarTodosOsUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> listarUsuarioPorID(@PathVariable Long id) {
        if (usuarioService.usuarioIsEmpty(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioService.listarUsuarioPorId(id));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioExibicaoDTO> cadastrarUsuario(@RequestBody UsuarioCriacaoDTO usuarioCriacao) {
        try {
            return ResponseEntity.status(201).body(
                    usuarioService.cadastrarUsuario(usuarioCriacao)
            );
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioExibicaoDTO> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario){
        if (usuarioService.usuarioIsEmpty(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioService.atualizarUsuario(id,usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DesativarUsuario(@PathVariable Long id){
        if (usuarioService.usuarioIsEmpty(id)) {
            return ResponseEntity.status(404).build();
        }
        usuarioService.desativarUsuario(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDTO> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO){
        UsuarioTokenDTO usuarioTokenDTO = this.usuarioService.autenticar(usuarioLoginDTO);
        return ResponseEntity.ok(usuarioTokenDTO);
    }

    @GetMapping("/buscarCEP")
    public ResponseEntity<ViaCepResponse> getCEP(@RequestParam String cep) {
        return ResponseEntity.ok(viaCepIntegrationService.getCEP(cep));
    }

}
