package culinart.utils.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum StatusAtivoEnum {
    ATIVO("ATIVO"),
    INATIVO("INATIVO")
    ;
    private String status;
}
