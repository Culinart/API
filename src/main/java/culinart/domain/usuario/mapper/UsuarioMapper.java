package culinart.domain.usuario.mapper;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioDTO;

public class UsuarioMapper {
    public static Usuario of(UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioDTO.getNome());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPermissao(1);
        usuario.setIsAtivo(1);
        usuario.setEndereco(null);

        return usuario;
    }

    public static UsuarioDTO of(Usuario usuario){
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setNome(usuario.getNome());

        return usuarioDTO;
    }
}
