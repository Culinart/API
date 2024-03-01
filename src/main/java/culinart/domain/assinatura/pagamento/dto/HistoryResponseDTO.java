package culinart.domain.assinatura.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResponseDTO {
    private Integer charge_id;
    private LocalDate created_at;
    private String status;

}
