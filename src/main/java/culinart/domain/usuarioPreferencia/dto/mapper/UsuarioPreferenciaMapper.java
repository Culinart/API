package culinart.domain.usuarioPreferencia.dto.mapper;

import culinart.domain.preferencia.mapper.PreferenciaMapper;
import culinart.domain.usuario.dto.mapper.UsuarioMapper;
import culinart.domain.usuarioPreferencia.UsuarioPreferencia;
import culinart.domain.usuarioPreferencia.dto.UsuarioPreferenciaExibicaoDTO;

import java.util.stream.Collectors;

public class UsuarioPreferenciaMapper {
    public static UsuarioPreferenciaExibicaoDTO toDTO(UsuarioPreferencia usuarioPreferencia){
        return UsuarioPreferenciaExibicaoDTO.builder()
                .id(usuarioPreferencia.getId())
                .preferencia(PreferenciaMapper.toDTO(usuarioPreferencia.getPreferencia()))
                .build();
    }

}
