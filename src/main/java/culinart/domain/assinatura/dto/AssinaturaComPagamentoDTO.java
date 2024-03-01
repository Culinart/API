package culinart.domain.assinatura.dto;

import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssinaturaComPagamentoDTO {
    private AssinaturaDTO assinatura;
    private PagamentoDTO pagamento;
}
