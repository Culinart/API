package culinart.domain.fornecedor;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDate dataNascimento;
    @Column(name = "is_ativo")
    private Integer isAtivo;
    private LocalDate dataCriacao;
}
