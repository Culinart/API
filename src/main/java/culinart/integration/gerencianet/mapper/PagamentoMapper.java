package culinart.integration.gerencianet.mapper;

import culinart.domain.pagamento.Pagamento;
import culinart.domain.usuario.Usuario;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;

public class PagamentoMapper {
    public static Pagamento toEntity (PagamentoDTO pagamentoDTO, Usuario usuario){
        if (pagamentoDTO == null){
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
}
