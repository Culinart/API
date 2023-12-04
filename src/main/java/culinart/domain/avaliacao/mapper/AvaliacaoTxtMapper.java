package culinart.domain.avaliacao.mapper;

import culinart.domain.avaliacao.dto.AvaliacaoTxtDto;

import java.math.BigDecimal;
import java.util.List;

public class AvaliacaoTxtMapper {
    public static AvaliacaoTxtDto toTxtDto(Object[] linha){
        AvaliacaoTxtDto receitaAvaliacao = new AvaliacaoTxtDto();
        receitaAvaliacao.setNomeReceita((String) linha[0]);
        Long longQtdAvaliacoes =(java.lang.Long) linha[1];
        receitaAvaliacao.setQtd_avaliacoes((Integer) longQtdAvaliacoes.intValue());
        BigDecimal notaMedia =(java.math.BigDecimal) linha[2];
        receitaAvaliacao.setNotaMedia((Double) notaMedia.doubleValue());
        return receitaAvaliacao;
    }
}
