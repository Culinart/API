package culinart.domain.usuario.dto.mapper;

import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.UsuarioCriacaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.domain.usuario.dto.UsuarioInfoPessoalDTO;
import culinart.service.usuario.autenticacao.dto.UsuarioTokenDTO;
import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;

public class UsuarioMapper {
    public static Usuario toEntity(UsuarioCriacaoDTO usuarioCriacaoDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(usuarioCriacaoDTO.getNome());
        usuario.setEmail(usuarioCriacaoDTO.getEmail());
        usuario.setSenha(usuarioCriacaoDTO.getSenha());
        usuario.setTelefone(usuarioCriacaoDTO.getTelefone());
        usuario.setPermissao(PermissaoEnum.USUARIO);
        usuario.setIsAtivo(StatusAtivoEnum.INATIVO);

        return usuario;
    }

    public static UsuarioExibicaoDTO toDTO(Usuario usuario) {
        UsuarioExibicaoDTO usuarioExibicaoDTO = new UsuarioExibicaoDTO();

        usuarioExibicaoDTO.setId(usuario.getId());
        usuarioExibicaoDTO.setEmail(usuario.getEmail());
        usuarioExibicaoDTO.setNome(usuario.getNome());
        usuarioExibicaoDTO.setTelefone(usuario.getTelefone());
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

    public static Usuario toEntity(UsuarioInfoPessoalDTO usuarioInfo) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioInfo.getNome());
        usuario.setEmail(usuarioInfo.getEmail());
        usuario.setTelefone(usuarioInfo.getTelefone());
        return usuario;
    }

}
