package culinart.domain.receitaPreferencia.mapper;


import culinart.domain.preferencia.mapper.PreferenciaMapper;
import culinart.domain.receitaPreferencia.ReceitaPreferencia;
import culinart.domain.receitaPreferencia.dto.ReceitaPreferenciaExibicaoDTO;

public class ReceitaPreferenciaMapper {

    public static ReceitaPreferenciaExibicaoDTO toDTO(ReceitaPreferencia receitaPreferencia) {
        return ReceitaPreferenciaExibicaoDTO.builder()
                .id(receitaPreferencia.getId())
                .preferencia(PreferenciaMapper.toDTO(receitaPreferencia.getPreferencia()))
                .build();
    }
    
}
