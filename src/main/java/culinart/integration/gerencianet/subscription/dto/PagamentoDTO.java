package culinart.integration.gerencianet.subscription.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {
    private int idAssinatura;
    private String statusAssinatura;
    private int idTransacao;
    private String statusTransacao;
    private String linkCobranca;
    private LocalDate dataExpiracao;
}
