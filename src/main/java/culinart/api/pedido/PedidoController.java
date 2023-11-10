package culinart.api.pedido;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.domain.fornecedor.dto.FuncionarioExibicaoDTO;
import culinart.domain.pedido.Pedido;
import culinart.service.pedido.PedidoService;
import culinart.utils.enums.StatusPedidoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping("/entrega/{idUser}")
    public ResponseEntity<Pedido> proximoPedido(@PathVariable int idUser) {
        Optional<Pedido> pedido = pedidoService.nextPedido(idUser);
        if(pedido.isEmpty()){
             return ResponseEntity.noContent().build();
         }
        return ResponseEntity.ok(pedido.get());

    }


    @PutMapping("/pularEntrega/{idPedido}")
    public ResponseEntity pularEntrega(@PathVariable int idPedido){
        Optional<Pedido> pedido = pedidoService.pularEntrega(idPedido);
        if(pedido.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else if (pedido.get().getStatus() == StatusPedidoEnum.CANCELADO){
            pedidoService.setDescontoPlano(pedido.get().getValor(), pedido.get().getPlano().getId());
            return ResponseEntity.ok(pedido.get());
        }
        return ResponseEntity.badRequest().build();
    }


    @PutMapping
    public ResponseEntity<Pedido> alterPedido(@RequestBody Pedido pedido){
        Pedido pedidoAtt = pedidoService.updatePedido(pedido);
        if (pedidoAtt != null){
            return ResponseEntity.status(200).body(pedidoAtt);
        }else{
            return ResponseEntity.status(400).build();
        }
    }
}
