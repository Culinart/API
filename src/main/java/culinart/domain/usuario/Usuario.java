package culinart.domain.usuario;

import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String senha;
    private PermissaoEnum permissao;
    private String telefone;

    @Column(name = "is_ativo")
    private StatusAtivoEnum isAtivo;

}
