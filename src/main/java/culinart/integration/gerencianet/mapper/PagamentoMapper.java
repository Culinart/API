package culinart.integration.gerencianet.mapper;

import culinart.domain.pagamento.Pagamento;
import culinart.domain.usuario.Usuario;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;

import java.time.LocalDate;

public class PagamentoMapper {
    public static Pagamento toEntity(PagamentoDTO pagamentoDTO, Usuario usuario) {
        if (pagamentoDTO == null) {
            return null;
        }
        return Pagamento.builder()
                .id(0)
                .idTransacao(pagamentoDTO.getIdTransacao())
                .idAssinatura(pagamentoDTO.getIdAssinatura())
                .dataExpiracao(pagamentoDTO.getDataExpiracao())
                .linkCobranca(pagamentoDTO.getLinkCobranca())
                .statusTransacao(pagamentoDTO.getStatusTransacao())
                .statusAssinatura(pagamentoDTO.getStatusAssinatura())
                .usuario(usuario)
                .build();
    }

    public static PagamentoDTO toDTO(Pagamento pagamento) {
        if (pagamento == null) {
            return null;
        }

        return PagamentoDTO.builder()
                .idAssinatura(pagamento.getIdAssinatura())
                .statusAssinatura(pagamento.getStatusAssinatura())
                .idTransacao(pagamento.getIdTransacao())
                .statusTransacao(pagamento.getStatusTransacao())
                .linkCobranca(pagamento.getLinkCobranca())
                .dataExpiracao(pagamento.getDataExpiracao())
                .build();
    }
}
