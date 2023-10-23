package culinart.domain.usuario;

import culinart.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    @OneToMany
    private List<Endereco> endereco;
    private Integer permissao;

    @Column(name = "is_ativo")
    private Integer isAtivo;

}
