package culinart.domain.avaliacao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvaliacaoCadastroDTO {
    private int idUsuario;
    private int idReceita;
    private Integer nota;
}
