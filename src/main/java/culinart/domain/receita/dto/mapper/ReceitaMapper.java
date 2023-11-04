package culinart.domain.receita.dto.mapper;

import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaExibicaoDTO;

public class ReceitaMapper {
    public static ReceitaExibicaoDTO toDTO(Receita receita){
        return ReceitaExibicaoDTO.builder()
                .id(receita.getId())
                .nome(receita.getNome())
                .tempoPreparo(receita.getTempoPreparo())
                .descricao(receita.getDescricao())
                .build();
    }
}
