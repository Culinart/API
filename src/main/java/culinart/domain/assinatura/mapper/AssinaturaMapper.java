package culinart.domain.assinatura.mapper;

import culinart.domain.assinatura.Assinatura;
import culinart.domain.assinatura.dto.AssinaturaDTO;
import culinart.domain.usuario.Usuario;

public class AssinaturaMapper {
    public static Assinatura toEntity(AssinaturaDTO assinatura, Usuario usuario) {
        return Assinatura.builder()
                .assinaturaId(assinatura.getIdAssinatura())
                .statusAssinatura(assinatura.getStatusAssinatura())
                .usuario(usuario)
                .build();
    }
}
