package culinart.api.assinatura.pagamento;

import culinart.integration.gerencianet.mapper.PagamentoMapper;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import culinart.service.assinatura.pagamento.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {
    private final PagamentoService pagamentoService;


    @PutMapping("/atualizar/status/{idUsuario}")
    public ResponseEntity<List<PagamentoDTO>> atualizarStatusPagamento(@PathVariable int idUsuario){
        return ResponseEntity.ok(pagamentoService.atualizarStatusPagamento(idUsuario)
                .stream().map(PagamentoMapper::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("verificar-inadimplencia/{idUsuario}")
    public ResponseEntity<Boolean> verificarPagamentoMaisDeDoisPagamentosPendentes(@PathVariable int idUsuario){
        return ResponseEntity.ok(pagamentoService.verificarSeExisteDoisPagamentosPendentes(idUsuario));
    }
}
