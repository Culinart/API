package culinart.api.pedido;

import culinart.domain.fornecedor.Funcionario;
import culinart.domain.fornecedor.dto.FuncionarioDTO;
import culinart.domain.fornecedor.dto.FuncionarioExibicaoDTO;
import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.dto.DatasPedidosDto;
import culinart.domain.pedido.dto.PedidoByDataDto;
import culinart.domain.pedido.dto.ProximosPedidosDto;
import culinart.domain.pedido.mapper.PedidoByDataMapper;
import culinart.domain.pedido.mapper.PedidoMapper;
import culinart.service.pedido.PedidoService;
import culinart.utils.Mapper;
import culinart.utils.enums.StatusPedidoEnum;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@Data
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;


    @GetMapping("/entrega/{idUser}")
    public ResponseEntity<PedidoByDataDto> proximoPedido(@PathVariable int idUser, @RequestBody Pedido dataEntrega) {

        List<Object[]> pedido = pedidoService.nextPedido(idUser, dataEntrega.getDataEntrega());
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
