package culinart.domain.fornecedor;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    @Column(name = "data_contratacao")
    private LocalDate dataContratacao;
    private String cpf;
    private String tel;
    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;
    private String rg;
    private String cargo;
    private String turno;
    private Integer permissao;
    private String senha;
    @Column(name = "is_ativo")
    private Integer isAtivo;
    private LocalDate dataCriacao;
}
