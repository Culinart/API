package culinart.domain.pedido.dto;

import lombok.Data;

@Data
public class DeletarReceitaPedidoDto {
    private int receita_id;
    private int pedido_id;
}
