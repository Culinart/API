package culinart.api.pagamento;

import culinart.integration.gerencianet.PagamentoService;
import culinart.integration.gerencianet.mapper.PagamentoMapper;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import culinart.integration.gerencianet.subscription.dto.PlanoDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;

    @PostMapping("/plano")
    public ResponseEntity<PlanoDataDTO> criarPlano(){
        return ResponseEntity.ok(pagamentoService.criarPlano());
    }

    @PostMapping("/solicitar/{idUsuario}")
    public ResponseEntity<PagamentoDTO> solicitarPagamento(@PathVariable int idUsuario){
        criarPlano();
        return ResponseEntity.ok(pagamentoService.criarPagamentoSubscricao(idUsuario));
    }

    @DeleteMapping("/assinatura/{idAssinatura}")
    public ResponseEntity<Void> cancelarAssinatura(@PathVariable int idAssinatura){
        pagamentoService.cancelarAssinatura(idAssinatura);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/plano/{idPlano}")
    public ResponseEntity<Void> cancelarPlano(@PathVariable int idPlano){
        pagamentoService.cancelarPlano(idPlano);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<PagamentoDTO> solicitarVisualizacaoUltimoPagamentoUsuario(@PathVariable int idUsuario){
        return ResponseEntity.ok(pagamentoService.solicitarVisualizacaoUltimoPagamentoUsuario(idUsuario));
    }
}
