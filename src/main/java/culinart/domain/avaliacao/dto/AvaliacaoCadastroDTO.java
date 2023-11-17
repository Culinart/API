package culinart.domain.avaliacao.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvaliacaoCadastroDTO {
    private int idUsuario;
    private int idReceita;
    @Min(value = 0, message = "Valor de mota negativo")
    @Max(value = 5, message = "Valor de nota excedida")
    private Integer nota;
}
