package culinart.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



 @Getter
 @AllArgsConstructor
 @NoArgsConstructor
public enum StatusPedidoEnum {
    PENDENTE ("PENDENTE"),
    NEXT ("NEXT"),
    ENTREGUE ("ENTREGUE"),
    CANCELADO ("CANCELADO");

    private String status;

}
