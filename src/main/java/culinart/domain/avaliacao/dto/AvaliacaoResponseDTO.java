package culinart.domain.avaliacao.dto;

import culinart.domain.receita.dto.ReceitaExibicaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvaliacaoResponseDTO {
    private int id;
    private UsuarioExibicaoDTO usuario;
    private ReceitaExibicaoDTO receita;
    private Integer nota;
}
