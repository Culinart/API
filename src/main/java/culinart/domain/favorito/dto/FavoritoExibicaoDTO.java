package culinart.domain.favorito.dto;

import culinart.domain.receita.dto.ReceitaExibicaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FavoritoExibicaoDTO {
    private int id;
    private ReceitaExibicaoDTO receita;
    private UsuarioExibicaoDTO usuario;
}
