package culinart.domain.receita.dto.mapper;

import culinart.domain.ingrediente.dto.mapper.IngredienteMapper;
import culinart.domain.modoPreparo.dto.mapper.ModoPreparoMapper;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaExibicaoDTO;

import java.util.stream.Collectors;

public class ReceitaMapper {
    public static ReceitaExibicaoDTO toDTO(Receita receita){
        return ReceitaExibicaoDTO.builder()
                .id(receita.getId())
                .nome(receita.getNome())
                .tempoPreparo(receita.getTempoPreparo())
                .descricao(receita.getDescricao())
                .ingredientes(receita.getIngredientes()
                        .stream().map(IngredienteMapper::toDTO).collect(Collectors.toList()))
                .modoPreparos(receita.getModoPreparos()
                        .stream().map(ModoPreparoMapper::toDTO).collect(Collectors.toList()))
                .build();
    }
}
