package culinart.domain.receitaCategoria.mapper;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.mapper.CategoriaMapper;
import culinart.domain.receita.dto.ReceitaCadastroDTO;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaExibicaoDTO;

public class ReceitaCategoriaMapper {
    public static ReceitaCategoriaExibicaoDTO toDTO(ReceitaCategoria receitaCategoria) {
        return ReceitaCategoriaExibicaoDTO.builder()
                .id(receitaCategoria.getId())
                .categoria(CategoriaMapper.toDTO(receitaCategoria.getCategoria()))
                .build();
    }

    public static ReceitaCategoria toEntity(ReceitaCategoriaExibicaoDTO categoriaDTO) {
        if (categoriaDTO == null || categoriaDTO.getCategoria() == null) {
            return null;
        }

        Categoria categoria = CategoriaMapper.toEntity(categoriaDTO.getCategoria());

        return ReceitaCategoria.builder()
                .categoria(categoria)
                .build();
    }


}
