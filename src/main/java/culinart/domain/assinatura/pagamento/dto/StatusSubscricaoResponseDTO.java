package culinart.domain.assinatura.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusSubscricaoResponseDTO {
    private Integer ocurrences;
    private Integer charge_id;
    private String notification_url;
    private LocalDate next_expire_at;
    private Integer discount;
    private LocalDate created_at;
    private List<HistoryResponseDTO> history;
    private Integer subscription_id;;
    private LocalDate next_execute;
    private Integer value;
    private PlanoResponseDTO plan;
    private String payment_method;
    private String status;

}
