package culinart.domain.funcionario.dto;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FuncionarioExibicaoDTO {
        private Integer id;
        private String nome;
        private String email;
        private Integer permissao;
        private String cpf;
        private String tel;
        private String area;
        private String cargo;
        private String turno;
}
