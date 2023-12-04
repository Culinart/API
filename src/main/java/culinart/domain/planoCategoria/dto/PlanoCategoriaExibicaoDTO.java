package culinart.domain.planoCategoria.dto;

import culinart.domain.categoria.dto.CategoriaExibicaoDTO;
import culinart.domain.plano.dto.PlanoExibicaoDTO;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlanoCategoriaExibicaoDTO {
    private int id;
    private PlanoExibicaoDTO plano;
    private CategoriaExibicaoDTO categoria;
}
