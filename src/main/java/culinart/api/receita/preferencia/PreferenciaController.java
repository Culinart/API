package culinart.api.receita.preferencia;

import culinart.domain.preferencia.Preferencia;
import culinart.domain.preferencia.dto.PreferenciaExibicaoDTO;
import culinart.domain.preferencia.mapper.PreferenciaMapper;
import culinart.service.receita.preferencia.PreferenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/preferencias")
@RequiredArgsConstructor
public class PreferenciaController {
    private final PreferenciaService preferenciaService;

    @GetMapping
    public ResponseEntity<List<PreferenciaExibicaoDTO>> exibirTodasPreferencia() {
        List<Preferencia> preferencias = preferenciaService.exibirTodasPreferencia();
        if (preferencias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PreferenciaExibicaoDTO> receitaExibicaoDTOS = new ArrayList<>();
        for (Preferencia preferencia : preferencias) {
            receitaExibicaoDTOS.add(PreferenciaMapper.toDTO(preferencia));
        }

        return ResponseEntity.ok(receitaExibicaoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreferenciaExibicaoDTO> exibirPreferenciaPorId(@PathVariable int id){
        return ResponseEntity.ok(PreferenciaMapper.toDTO(preferenciaService.exibirPreferenciaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<PreferenciaExibicaoDTO> cadastrarPreferencia(@RequestBody Preferencia preferencia){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(PreferenciaMapper.toDTO(preferenciaService.cadastrarPreferencia(preferencia)));
    };

    @PutMapping("/{id}")
    public ResponseEntity<PreferenciaExibicaoDTO> atualizarPreferencia(@PathVariable int id,@RequestBody Preferencia preferencia){
        return ResponseEntity.ok(PreferenciaMapper.toDTO(preferenciaService.atualizarPreferencia(id,preferencia)));
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPreferencia(@PathVariable int id){
        preferenciaService.deletarPreferencia(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
