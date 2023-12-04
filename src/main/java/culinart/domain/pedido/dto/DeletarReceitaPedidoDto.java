package culinart.domain.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletarReceitaPedidoDto {
    private int receita_id;
    private int pedido_id;
}
