package culinart.domain.avaliacao.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvaliacaoResponseDTO {
    private int id;
    private Integer nota;
}
