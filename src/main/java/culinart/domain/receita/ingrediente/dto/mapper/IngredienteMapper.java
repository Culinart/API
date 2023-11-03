package culinart.domain.receita.ingrediente.dto.mapper;

import culinart.domain.receita.ingrediente.Ingrediente;
import culinart.domain.receita.ingrediente.dto.IngredienteExibicaoDTO;

public class IngredienteMapper {
    public static IngredienteExibicaoDTO toDTO(Ingrediente ingrediente) {
        if(ingrediente == null){
            return null;
        }
        return IngredienteExibicaoDTO.builder()
                .id(ingrediente.getId())
                .nome(ingrediente.getNome())
                .quantidade(ingrediente.getQuantidade())
                .unidadeMedidaEnum(ingrediente.getUnidadeMedidaEnum())
                .build();
    }
}
