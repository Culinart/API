package culinart.domain.assinatura.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssinaturaDTO {
    private int idAssinatura;
    private String statusAssinatura;
}
