package culinart.api.pedido;

import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.dto.DatasPedidosDto;
import culinart.domain.pedido.dto.DeletarReceitaPedidoDto;
import culinart.domain.pedido.dto.PedidoByDataDto;
import culinart.domain.pedido.dto.ProximosPedidosDto;
import culinart.domain.pedido.mapper.PedidoByDataMapper;
import culinart.domain.pedido.mapper.PedidoMapper;
import culinart.domain.plano.Plano;
import culinart.service.pedido.PedidoService;
import culinart.utils.enums.StatusPedidoEnum;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Data
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;


    @PostMapping("/entrega/{idUser}")
    public ResponseEntity<PedidoByDataDto> proximoPedido(@PathVariable int idUser, @RequestBody String dataEntrega) {

        String dataEntregaSemAspas = dataEntrega.substring(1, dataEntrega.length() - 1);
        LocalDate dataDaEntrega = LocalDate.parse(dataEntregaSemAspas);

        List<Object[]> pedido = pedidoService.nextPedido(idUser, dataDaEntrega);
        if (pedido.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        PedidoByDataDto pedidoFormatado = PedidoByDataMapper.toPedidoByDataDto(pedido);
        return ResponseEntity.ok().body(pedidoFormatado);


    }

    @GetMapping("/datas/{idUser}")
    public ResponseEntity<List<DatasPedidosDto>> datas(@PathVariable int idUser){
        List <Pedido> listaPedidos = pedidoService.getDatas(idUser);
        if (listaPedidos.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        PedidoMapper mapper = new PedidoMapper();
        List <DatasPedidosDto> listaDatas = listaPedidos.stream()
                .map(mapper::toDatasPedidosDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaDatas);
    }


    @PutMapping("/pularEntrega/{idPedido}")
    public ResponseEntity<Pedido> pularEntrega(@PathVariable int idPedido){
        Optional<Pedido> pedido = pedidoService.pularEntrega(idPedido);
        if(pedido.isEmpty()){
            return ResponseEntity.badRequest().build();
        }else if (pedido.get().getStatus() == StatusPedidoEnum.CANCELADO){
            pedidoService.setDescontoPlano(pedido.get().getValor(), pedido.get().getPlano().getId());
            int userId = pedido.get().getPlano().getUsuario().getId();
            Plano plano = pedido.get().getPlano();
            LocalDate dataUltimoPedido = pedido.get().getDataEntrega();
            pedidoService.criarPedido(userId, plano, "Pedido", dataUltimoPedido);
            return ResponseEntity.ok(pedido.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/entregue/{idPedido}")
    public ResponseEntity<Pedido> pedidoEntregue(@PathVariable int idPedido){
        Optional<Pedido> pedido = pedidoService.pedidoEntregue(idPedido);
        return pedido.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @DeleteMapping("/deletar/{receitaId}/{pedidoId}")
    public ResponseEntity<Pedido> alterPedidoDeletar(@PathVariable int receitaId, @PathVariable int pedidoId){
        DeletarReceitaPedidoDto receita = new DeletarReceitaPedidoDto(receitaId, pedidoId);
        pedidoService.deletarReceitaPedido(receita);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/adicionar")
    public ResponseEntity<Pedido> alterPedidoAdicionar(@RequestBody DeletarReceitaPedidoDto receita){
        pedidoService.addReceitaPedido(receita);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/proximas")
    public ResponseEntity<List<ProximosPedidosDto>> proximoPedido() {

        List<Object[]> pedidos = pedidoService.proximasEntregas();
        if (pedidos.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        List<ProximosPedidosDto> listaProxPedidos = pedidos.stream()
                .map(PedidoMapper::toProximosPedidosDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(listaProxPedidos);

    }
}
