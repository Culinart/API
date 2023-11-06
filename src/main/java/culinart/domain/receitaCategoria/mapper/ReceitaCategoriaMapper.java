package culinart.domain.receitaCategoria.mapper;

import culinart.domain.categoria.mapper.CategoriaMapper;
import culinart.domain.preferencia.mapper.PreferenciaMapper;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaExibicaoDTO;

import java.util.stream.Collectors;

public class ReceitaCategoriaMapper {
    public static ReceitaCategoriaExibicaoDTO toDTO(ReceitaCategoria receitaCategoria) {
        return ReceitaCategoriaExibicaoDTO.builder()
                .id(receitaCategoria.getId())
                .receitaDTO(ReceitaMapper.toDTO(receitaCategoria.getReceita()))
                .categoriaDTO(receitaCategoria.getCategoria().
                        stream().map(CategoriaMapper::toDTO).collect(Collectors.toList()))
                .preferenciaDTO(receitaCategoria.getPreferencia()
                        .stream().map(PreferenciaMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
