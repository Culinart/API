package culinart.domain.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
public class DatasPedidosDto {
    private LocalDate datasPedidos;
}
