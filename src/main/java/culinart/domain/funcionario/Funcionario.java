package culinart.domain.funcionario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Funcionario {
    @Id
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private Integer permissao;
    private String cpf;
    private String tel;
    private String area;
    private String cargo;
    private String turno;
    @Column(name = "data_nascimento")
    private String dataNascimento;
    @Column(name = "is_ativo")
    private Integer isAtivo;
}
