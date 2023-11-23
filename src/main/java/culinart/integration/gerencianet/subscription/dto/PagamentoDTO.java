package culinart.integration.gerencianet.subscription.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PagamentoDTO {
    private int idAssinatura;
    private String statusAssinatura;
    private int idTransacao;
    private String statusTransacao;
    private String linkCobranca;
    private LocalDate dataExpiracao;
}
