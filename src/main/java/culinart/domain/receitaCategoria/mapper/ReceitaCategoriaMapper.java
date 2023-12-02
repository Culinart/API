package culinart.domain.receitaCategoria.mapper;

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
}
