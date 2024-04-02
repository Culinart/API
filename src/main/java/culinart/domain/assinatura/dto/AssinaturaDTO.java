package culinart.domain.assinatura.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssinaturaDTO {
    private int idAssinatura;
    private String statusAssinatura;
}
