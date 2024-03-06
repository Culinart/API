package culinart.domain.assinatura.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanoResponseDTO {
    private Integer repeats;
    private String name;
    private Integer interval;
    private Integer plan_id;
}
