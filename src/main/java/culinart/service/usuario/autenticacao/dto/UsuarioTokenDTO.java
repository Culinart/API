package culinart.service.usuario.autenticacao.dto;

import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioTokenDTO {
    private int userID;
    private String nome;
    private String email;
    private String token;
    @Enumerated(EnumType.STRING)
    private StatusAtivoEnum isAtivo;
    @Enumerated(EnumType.STRING)
    private PermissaoEnum permissao;
}
