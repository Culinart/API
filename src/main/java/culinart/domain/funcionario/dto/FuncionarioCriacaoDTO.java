package culinart.domain.funcionario.dto;

import lombok.Getter;
import lombok.Setter;

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
    private String dataNascimento;
    private Integer permissao;
}
