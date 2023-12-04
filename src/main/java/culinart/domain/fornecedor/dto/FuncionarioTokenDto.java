package culinart.domain.fornecedor.dto;

import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class FuncionarioTokenDto {
    private int funcId;
    private String nome;
    private String email;
    private String token;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private PermissaoEnum permissao;
}
