package culinart.api.usuario;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioDTO;
import culinart.domain.usuario.dto.UsuarioDetalhesDto;
import culinart.service.usuario.UsuarioService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<UsuarioDetalhesDto>> listarTodosOsFilmes() {
        if (usuarioService.buscaNaoPossuiResultado()) {
            return ResponseEntity.status(204).build();
        }
        return ResponseEntity.status(200).body(usuarioService.listarTodosOsFilmes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarFilmePorId(@PathVariable Long id) {
        if (!usuarioService.filmeIsPresent(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioService.listarFilmePorId(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> cadastrarFilme(@RequestBody Usuario usuario) {
        try {
            return ResponseEntity.status(201).body(usuarioService.cadastrarFilme(usuario));
        } catch (Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarFilme(@PathVariable Long id, @RequestBody Usuario usuario){
        if (!usuarioService.filmeIsPresent(id)) {
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.status(200).body(usuarioService.atualizarFilme(id,usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Long id){
        if (!usuarioService.filmeIsPresent(id)) {
            return ResponseEntity.status(404).build();
        }
        usuarioService.deletarFilme(id);
        return ResponseEntity.status(204).build();
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }
}
