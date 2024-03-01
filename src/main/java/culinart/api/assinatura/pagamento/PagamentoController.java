package culinart.api.assinatura.pagamento;

import culinart.service.assinatura.pagamento.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;


//    @PutMapping("/atualizar/status/{idUsuario}")
//    public ResponseEntity<PagamentoDTO> atualizarStatusPagamento(@PathVariable int idUsuario){
//        return ResponseEntity.ok(pagamentoService.atualizarStatusPagamento(idUsuario));
//    }
}
