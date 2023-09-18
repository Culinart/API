package culinart.service.usuario.Utils;

import culinart.domain.usuario.dto.UsuarioDTO;
import culinart.domain.usuario.repository.UsuarioRepository;

public class UsuarioUtils {

    public static Boolean validaEmailUsuarioDTO(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)" +
                "*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");
    }

    public static Boolean validaSenhaUsuarioDTO(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getSenha().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public static Boolean validaNomeUsuarioDTO(UsuarioDTO usuarioDTO) {
        return usuarioDTO.getNome().matches("^[A-Za-z]{29,}");
    }

    public static Boolean validaIdUsuario(Long id, UsuarioRepository usuarioRepository){
        return id > 0 && id < usuarioRepository.count();
    }
}
