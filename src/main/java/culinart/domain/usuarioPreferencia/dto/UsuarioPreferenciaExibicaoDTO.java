package culinart.domain.usuarioPreferencia.dto;

import culinart.domain.preferencia.dto.PreferenciaExibicaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UsuarioPreferenciaExibicaoDTO {

    private Integer id;
    private UsuarioExibicaoDTO usuario;
    private List<PreferenciaExibicaoDTO> preferencia;
}
