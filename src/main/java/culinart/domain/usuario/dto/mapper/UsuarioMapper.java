package culinart.domain.usuario.dto.mapper;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;

public class UsuarioMapper {
    public static Usuario toDTO(UsuarioCriacaoDTO usuarioCriacaoDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDTO.getNome());
        usuario.setEmail(usuarioCriacaoDTO.getEmail());
        usuario.setSenha(usuarioCriacaoDTO.getSenha());
        usuario.setPermissao(1);
        usuario.setIsAtivo(1);

        return usuario;
    }

    public static UsuarioExibicaoDTO toDTO(Usuario usuario) {
        UsuarioExibicaoDTO usuarioExibicaoDTO = new UsuarioExibicaoDTO();

        usuarioExibicaoDTO.setId(usuario.getId());
        usuarioExibicaoDTO.setEmail(usuario.getEmail());
        usuarioExibicaoDTO.setNome(usuario.getNome());
        usuarioExibicaoDTO.setIsAtivo(usuario.getIsAtivo());

        return usuarioExibicaoDTO;
    }

    public static UsuarioTokenDTO toDTO(Usuario usuario, String token) {
        UsuarioTokenDTO usuarioTokenDTO = new UsuarioTokenDTO();
        usuarioTokenDTO.setUserID(usuario.getId());
        usuarioTokenDTO.setEmail(usuario.getEmail());
        usuarioTokenDTO.setNome(usuario.getNome());
        usuarioTokenDTO.setToken(token);
        return usuarioTokenDTO;
    }

}
