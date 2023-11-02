package culinart.api.plano;

import culinart.domain.plano.dto.PlanoCadastroDTO;
import culinart.domain.plano.dto.PlanoExibicaoDTO;
import culinart.service.plano.PlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;


    @GetMapping
    public ResponseEntity<List<PlanoExibicaoDTO>> listarTodosOsPlanos(){
        List<PlanoExibicaoDTO> lista = planoService.listarTodosOsPlanos();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<PlanoExibicaoDTO> listarPlanoPorIdUsuario(@PathVariable int idUsuario){
       return ResponseEntity.ok(planoService.listarPorId(idUsuario));
    }

    @PostMapping("/{idUsuario}")
    public ResponseEntity<PlanoExibicaoDTO> cadastrarPlano(@RequestBody PlanoCadastroDTO plano, @PathVariable int idUsuario){
        return ResponseEntity.status(HttpStatus.CREATED).body(planoService.cadastrarPlano(plano, idUsuario));
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<PlanoExibicaoDTO> atualizarPlano(@RequestBody PlanoCadastroDTO novoPlano, @PathVariable int idUsuario){
        return ResponseEntity.ok(planoService.atualizarPlano(novoPlano,idUsuario));
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletarPlano(@PathVariable int idUsuario){
        planoService.deletarPlano(idUsuario);
        return ResponseEntity.noContent().build();
    }
}
