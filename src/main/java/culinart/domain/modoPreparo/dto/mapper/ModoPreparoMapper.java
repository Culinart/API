package culinart.domain.modoPreparo.dto.mapper;

import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.modoPreparo.dto.ModoPreparoExibicaoDTO;

public class ModoPreparoMapper {
    public static ModoPreparoExibicaoDTO toDTO (ModoPreparo modoPreparo){
        if(modoPreparo == null){
            return null;
        }

        return ModoPreparoExibicaoDTO.builder()
                .id(modoPreparo.getId())
                .passo(modoPreparo.getPasso())
                .build();
    }
}
