package culinart.domain.usuario.dto;

import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioExibicaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private StatusAtivoEnum isAtivo;
    @Enumerated(EnumType.STRING)
    private PermissaoEnum permissao;

}
