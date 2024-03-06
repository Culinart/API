package culinart.integration.gerencianet.mapper;

import culinart.domain.assinatura.Assinatura;
import culinart.domain.assinatura.pagamento.Pagamento;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;

public class PagamentoMapper {
    public static Pagamento toEntity(PagamentoDTO pagamentoDTO, Assinatura assinatura) {
        if (pagamentoDTO == null) {
            return null;
        }
        return Pagamento.builder()
                .id(0)
                .transacaoId(pagamentoDTO.getIdTransacao())
                .dataExpiracao(pagamentoDTO.getDataExpiracao())
                .linkCobranca(pagamentoDTO.getLinkCobranca())
                .statusTransacao(pagamentoDTO.getStatusTransacao())
                .assinatura(assinatura)
                .build();
    }

    public static PagamentoDTO toDTO(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }

        return PagamentoDTO.builder()
                .idTransacao(pagamento.getTransacaoId())
                .statusTransacao(pagamento.getStatusTransacao())
                .linkCobranca(pagamento.getLinkCobranca())
                .dataExpiracao(pagamento.getDataExpiracao())
                .build();
    }
}
