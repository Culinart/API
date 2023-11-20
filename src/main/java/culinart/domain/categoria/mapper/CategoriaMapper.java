package culinart.domain.categoria.mapper;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.dto.CategoriaExibicaoDTO;

public class CategoriaMapper {
    public static CategoriaExibicaoDTO toDTO(Categoria categoria){
        return CategoriaExibicaoDTO.builder()
                .id(categoria.getId())
                .nome(categoria.getNome())
                .valor(categoria.getValor())
                .build();
    }
}
