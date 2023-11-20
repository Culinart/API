package culinart.domain.pedido.mapper;

import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.dto.DatasPedidosDto;
import culinart.domain.pedido.dto.ProximosPedidosDto;

import java.math.BigDecimal;
public class PedidoMapper {
    public DatasPedidosDto toDatasPedidosDto(Pedido pedido){
        return new DatasPedidosDto(pedido.getDataEntrega());
    }

    public static ProximosPedidosDto toProximosPedidosDto(Object[] linha){
        ProximosPedidosDto pedidoDto = new ProximosPedidosDto();
        pedidoDto.setIdPedido((Integer) linha[0]);
        java.sql.Date dataSql = (java.sql.Date) linha[1];
        pedidoDto.setDataEntrega(dataSql.toLocalDate());
        pedidoDto.setNomeUsuario((String) linha[2]);
        pedidoDto.setLogradouro((String) linha[3]);
        pedidoDto.setNumero((Integer) linha[4]);
        pedidoDto.setReceitas((String) linha[5]);
        pedidoDto.setCategorias((String) linha[6]);
        Long longQtdReceitas =(java.lang.Long) linha[7];
        pedidoDto.setQtdReceitas((Integer) longQtdReceitas.intValue());
        BigDecimal longQtdPorcoes =(java.math.BigDecimal) linha[8];
        pedidoDto.setQtdPorcoes((Integer) longQtdPorcoes.intValue());
        return pedidoDto;
    }
}
