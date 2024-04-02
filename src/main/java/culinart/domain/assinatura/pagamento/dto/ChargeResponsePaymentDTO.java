package culinart.domain.assinatura.pagamento.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChargeResponsePaymentDTO {
    private String dataExpiracao;
    private String linkPagamento;
}
