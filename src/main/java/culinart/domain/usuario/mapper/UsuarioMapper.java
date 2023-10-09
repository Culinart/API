package culinart.domain.usuario.mapper;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioDetalhesDto;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;

import java.util.List;

public class UsuarioMapper {
    public static Usuario of(UsuarioCriacaoDTO usuarioCriacaoDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDTO.getNome());
        usuario.setEmail(usuarioCriacaoDTO.getEmail());
        usuario.setSenha(usuarioCriacaoDTO.getSenha());
        usuario.setPermissao(1);
        usuario.setIsAtivo(1);
        usuario.setEndereco(usuario.getEndereco());

        return usuario;
    }

    public static UsuarioExibicaoDTO of(Usuario usuario) {
        UsuarioExibicaoDTO usuarioExibicaoDTO = new UsuarioExibicaoDTO();

        usuarioExibicaoDTO.setId(usuario.getId());
        usuarioExibicaoDTO.setEmail(usuario.getEmail());
        usuarioExibicaoDTO.setNome(usuario.getNome());
        usuarioExibicaoDTO.setEndereco(usuario.getEndereco());

        return usuarioExibicaoDTO;
    }

    public static UsuarioTokenDTO of(Usuario usuario, String token) {
        UsuarioTokenDTO usuarioTokenDTO = new UsuarioTokenDTO();
        usuarioTokenDTO.setUserID(usuario.getId());
        usuarioTokenDTO.setEmail(usuario.getEmail());
        usuarioTokenDTO.setNome(usuario.getNome());
        usuarioTokenDTO.setToken(token);
        return usuarioTokenDTO;
    }

}
