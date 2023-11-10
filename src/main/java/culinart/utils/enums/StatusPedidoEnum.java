package culinart.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;



 @Getter
public enum StatusPedidoEnum {
    PROCESSAMENTO,
    ENVIADO,
    ENTREGUE,
    CANCELADO,
    NEXT;

}
