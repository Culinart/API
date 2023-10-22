package culinart.service.usuario.autenticacao.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioTokenDTO {
    private int userID;
    private String nome;
    private String email;
    private String token;

}
