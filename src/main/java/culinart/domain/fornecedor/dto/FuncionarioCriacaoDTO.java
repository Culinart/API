package culinart.domain.fornecedor.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class FuncionarioCriacaoDTO {
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String tel;
    private String area;
    private String cargo;
    private String turno;
    private LocalDate dataNascimento;
    private Integer permissao;
    private LocalDate dataCriacao;
}
