package culinart.api.receita.modoPreparo;

import culinart.domain.receita.modoPreparo.ModoPreparo;
import culinart.domain.receita.modoPreparo.dto.ModoPreparoExibicaoDTO;
import culinart.domain.receita.modoPreparo.dto.mapper.ModoPreparoMapper;
import culinart.service.receita.modoPreparo.ModoPreparoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/modos-preparos")
@RequiredArgsConstructor
public class ModoPreparoController {
    private final ModoPreparoService modoPreparoService;

    @GetMapping
    public ResponseEntity<List<ModoPreparoExibicaoDTO>> exibirTodosModosPreparos() {
        List<ModoPreparo> modoPreparos = modoPreparoService.exibirTodosModosPreparos();
        if (modoPreparos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ModoPreparoExibicaoDTO> modoPreparoExibicaoDTOS = new ArrayList<>();
        for (ModoPreparo modoPreparo : modoPreparos) {
            modoPreparoExibicaoDTOS.add(ModoPreparoMapper.toDTO(modoPreparo));
        }
        return ResponseEntity.ok(modoPreparoExibicaoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModoPreparoExibicaoDTO> exibirModoPreparoPorId(@PathVariable int id) {
        return ResponseEntity.ok(ModoPreparoMapper.toDTO(modoPreparoService.exibirModoPreparoPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ModoPreparoExibicaoDTO> cadastrarModoPreparo(@RequestBody ModoPreparo modoPreparo) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ModoPreparoMapper.toDTO(modoPreparoService.cadastrarModoPreparo(modoPreparo)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModoPreparoExibicaoDTO> atualizarModoPreparo(@PathVariable int id, @RequestBody ModoPreparo modoPreparo){
        return ResponseEntity.ok(ModoPreparoMapper.toDTO(modoPreparoService.atualizarModoPreparo(id,modoPreparo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarModoPreparo(@PathVariable int id){
        modoPreparoService.deletarModoPreparo(id);
        return ResponseEntity.noContent().build();
    }
}
