package culinart.domain.receitaCategoria.dto;

import culinart.domain.categoria.dto.CategoriaExibicaoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaCategoriaExibicaoDTO {
    private int id;
    private CategoriaExibicaoDTO categoria;
}
