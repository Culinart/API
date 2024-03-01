package culinart.api.assinatura;

import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import culinart.integration.gerencianet.subscription.dto.PlanoDataDTO;
import culinart.service.assinatura.AssinaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/assinaturas")
@RestController
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService assinaturaService;

    @PostMapping("/plano")
    public ResponseEntity<PlanoDataDTO> criarPlano() {
        return ResponseEntity.ok(assinaturaService.criarPlano());
    }

    @DeleteMapping("/assinatura/{idAssinatura}")
    public ResponseEntity<Void> cancelarAssinatura(@PathVariable int idAssinatura){
        assinaturaService.cancelarAssinatura(idAssinatura);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/plano/{idPlano}")
    public ResponseEntity<Void> cancelarPlano(@PathVariable int idPlano){
        assinaturaService.cancelarPlano(idPlano);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/solicitar/{idUsuario}")
    public ResponseEntity<PagamentoDTO> criarAssinatura(@PathVariable int idUsuario) {
        return ResponseEntity.ok(assinaturaService.criarAssinatura(idUsuario));
    }
}
