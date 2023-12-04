package culinart.domain.preferencia.mapper;

import culinart.domain.preferencia.Preferencia;
import culinart.domain.preferencia.dto.PreferenciaExibicaoDTO;

public class PreferenciaMapper {
    public static PreferenciaExibicaoDTO toDTO (Preferencia preferencia){
        return PreferenciaExibicaoDTO.builder()
                .id(preferencia.getId())
                .nome(preferencia.getNome())
                .tipoPreferenciaEnum(preferencia.getTipo())
                .corTexto(preferencia.getCorTexto())
                .corFundo(preferencia.getCorFundo())
                .build();
    }

    public static Preferencia toEntity(PreferenciaExibicaoDTO preferenciaDTO) {
        if (preferenciaDTO == null) {
            return null;
        }

        return Preferencia.builder()
                .id(preferenciaDTO.getId())
                .nome(preferenciaDTO.getNome())
                .tipo(preferenciaDTO.getTipoPreferenciaEnum())
                .corFundo(preferenciaDTO.getCorFundo())
                .corTexto(preferenciaDTO.getCorTexto())
                .build();
    }
}
