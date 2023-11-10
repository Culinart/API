package culinart.api.pedido;

import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.domain.pedido.Pedido;
import culinart.service.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public ResponseEntity<Pedido> buscarFuncionariosOrdenadosDTO(@PathVariable int idUser) {
        Optional<Pedido> pedido = pedidoService.nextPedido(idUser);
        if(pedido.isEmpty()){
             return ResponseEntity.noContent().build();
         }
        return ResponseEntity.ok(pedido.get());

    }
}
