package culinart.domain.fornecedor.dto;

import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;
import lombok.Data;
import java.time.LocalDate;


@Data
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
    private PermissaoEnum permissao;
    private StatusAtivoEnum isAtivo;
}
