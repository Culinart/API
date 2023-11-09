package culinart.api.usuario.preferencia;

import culinart.domain.preferencia.dto.PreferenciaDelecaoDTO;
import culinart.domain.usuarioPreferencia.dto.UsuarioPreferenciaCadastroDTO;
import culinart.domain.usuarioPreferencia.dto.UsuarioPreferenciaExibicaoDTO;
import culinart.domain.usuarioPreferencia.dto.mapper.UsuarioPreferenciaMapper;
import culinart.service.usuario.preferencia.UsuarioPreferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios/preferencias")
@RequiredArgsConstructor
public class UsuarioPreferenciaController {
    private final UsuarioPreferenciaService preferenciaService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<UsuarioPreferenciaExibicaoDTO> exibirPreferenciasDoUsuario(@PathVariable int idUsuario){
        return ResponseEntity.ok(UsuarioPreferenciaMapper.toDTO(
                preferenciaService.exibirTodasPreferenciasDeUsuario(idUsuario)));
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<UsuarioPreferenciaExibicaoDTO> cadastrarPreferenciasDoUsuario(
            @PathVariable int idUsuario,
            @RequestBody UsuarioPreferenciaCadastroDTO preferenciaDTO
    ){
        return ResponseEntity.ok(UsuarioPreferenciaMapper.toDTO(
                preferenciaService.cadastrarPreferenciasDoUsuario(idUsuario,preferenciaDTO)));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletarPreferenciasDoUsuario(
            @PathVariable int idUsuario,
            @RequestBody PreferenciaDelecaoDTO preferenciaDTO
    ){
        preferenciaService.deletarPreferenciasDoUsuario(idUsuario, preferenciaDTO);
        return ResponseEntity.noContent().build();
    }

}
