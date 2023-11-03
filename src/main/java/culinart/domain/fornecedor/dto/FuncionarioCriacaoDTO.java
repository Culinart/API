package culinart.domain.fornecedor.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class FuncionarioCriacaoDTO {
    private Integer id;
    private String nome;
    private String email;
    private LocalDate dataContratacao;
    private String cpf;
    private String tel;
    private LocalDate dataNascimento;
    private String rg;
    private String cargo;
    private String turno;
    private Integer permissao;
    private Integer isAtivo;
}
