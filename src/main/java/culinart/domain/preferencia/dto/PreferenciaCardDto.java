package culinart.domain.preferencia.dto;

import culinart.utils.enums.TipoPreferenciaEnum;
import lombok.Data;

@Data
public class PreferenciaCardDto {
    private String nome;
    private String corFundo;
    private String corTexto;
}
