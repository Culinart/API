package culinart.domain.assinatura.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponseDTO {
    private Integer charge_id;
    private String created_at;
    private String status;

}
