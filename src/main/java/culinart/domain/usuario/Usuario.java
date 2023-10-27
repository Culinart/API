package culinart.domain.usuario;

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
    private Integer permissao;

    @Column(name = "is_ativo")
    private Integer isAtivo;

}
