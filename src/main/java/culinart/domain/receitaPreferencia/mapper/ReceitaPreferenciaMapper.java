package culinart.domain.receitaPreferencia.mapper;


import culinart.domain.preferencia.Preferencia;
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

    public static ReceitaPreferencia toEntity(ReceitaPreferenciaExibicaoDTO preferenciaDTO) {
        if (preferenciaDTO == null) {
            return null;
        }

        Preferencia preferencia = PreferenciaMapper.toEntity(preferenciaDTO.getPreferencia()); // Make sure you have a toEntity method in PreferenciaMapper

        return ReceitaPreferencia.builder()
                .preferencia(preferencia)
                .build();
    }
    
}
