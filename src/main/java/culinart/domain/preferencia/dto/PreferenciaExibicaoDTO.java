package culinart.domain.preferencia.dto;

import culinart.utils.enums.TipoPreferenciaEnum;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PreferenciaExibicaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private TipoPreferenciaEnum tipoPreferenciaEnum;
    private String corFundo;
    private String corTexto;
}