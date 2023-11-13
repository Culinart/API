package culinart.domain.pedido.mapper;

import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.dto.DatasPedidosDto;

public class PedidoMapper {
    public DatasPedidosDto toDatasPedidosDto(Pedido pedido){
        return new DatasPedidosDto(pedido.getDataEntrega());
    }
}
