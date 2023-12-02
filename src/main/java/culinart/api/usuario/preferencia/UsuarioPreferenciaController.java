package culinart.api.usuario.preferencia;

import culinart.domain.preferencia.dto.PreferenciaDelecaoDTO;
import culinart.domain.preferencia.dto.PreferenciaExibicaoDTO;
import culinart.domain.preferencia.mapper.PreferenciaMapper;
import culinart.domain.usuarioPreferencia.UsuarioPreferencia;
import culinart.domain.usuarioPreferencia.dto.UsuarioPreferenciaExibicaoDTO;
import culinart.domain.usuarioPreferencia.dto.mapper.UsuarioPreferenciaMapper;
import culinart.service.usuario.preferencia.UsuarioPreferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios/preferencias")
@RequiredArgsConstructor
public class UsuarioPreferenciaController {
    private final UsuarioPreferenciaService preferenciaService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<UsuarioPreferenciaExibicaoDTO>> exibirPreferenciasDoUsuario(@PathVariable int idUsuario){
        List<UsuarioPreferencia> usuarioPreferencias = preferenciaService.exibirTodasPreferenciasDeUsuario(idUsuario);
        return ResponseEntity.ok(usuarioPreferencias.stream().map(UsuarioPreferenciaMapper::toDTO).toList());
    }

    @PostMapping("/{idPreferencia}/{idUsuario}")
    public ResponseEntity<UsuarioPreferenciaExibicaoDTO> cadastrarPreferenciasDoUsuario(
            @PathVariable int idUsuario,
            @PathVariable int idPreferencia
    ){
        return ResponseEntity.ok(UsuarioPreferenciaMapper.toDTO(
                preferenciaService.cadastrarPreferenciasDoUsuario(idUsuario,idPreferencia)));
    }

    @DeleteMapping("/{idUsuarioPreferencia}")
    public ResponseEntity<Void> deletarPreferenciasDoUsuario(
            @PathVariable int idUsuarioPreferencia
    ){
        preferenciaService.deletarPreferenciasDoUsuario(idUsuarioPreferencia);
        return ResponseEntity.noContent().build();
    }

}
