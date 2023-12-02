package culinart.domain.receitaPreferencia.dto;

import culinart.domain.preferencia.dto.PreferenciaExibicaoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaPreferenciaExibicaoDTO {
    private int id;
    private PreferenciaExibicaoDTO preferencia;
}
