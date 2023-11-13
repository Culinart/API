package culinart.domain.endereco.usuario.dto.mapper;

import culinart.domain.endereco.Endereco;
import culinart.domain.endereco.dto.mapper.EnderecoMapper;
import culinart.domain.endereco.usuario.EnderecoUsuario;
import culinart.domain.endereco.usuario.dto.EnderecoResponseToUsuarioDTO;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.mapper.UsuarioMapper;
import culinart.utils.enums.StatusAtivoEnum;

import java.util.ArrayList;
import java.util.List;

public class EnderecoUsuarioMapper {
    public static EnderecoUsuario toEntity(Endereco endereco, Usuario usuario){
        return new EnderecoUsuario(
                null,
                endereco,
                usuario,
                StatusAtivoEnum.ATIVO
        );
    }

    public static List<EnderecoResponseToUsuarioDTO> toDTOList(List<EnderecoUsuario> enderecoUsuarios) {
        if (enderecoUsuarios.isEmpty()) {
            return new ArrayList<>();
        }

        List<EnderecoResponseToUsuarioDTO> enderecoUsuariosDTO = new ArrayList<>();

        for (EnderecoUsuario enderecoUsuarioDaVez : enderecoUsuarios) {
            EnderecoResponseToUsuarioDTO dtoDaVez = new EnderecoResponseToUsuarioDTO(
                    enderecoUsuarioDaVez.getId(),
                    UsuarioMapper.toDTO(enderecoUsuarioDaVez.getUsuario()),
                    EnderecoMapper.toDTO(enderecoUsuarioDaVez.getEndereco()),
                    enderecoUsuarioDaVez.getIsAtivo()
            );
            enderecoUsuariosDTO.add(dtoDaVez);
        }

        return enderecoUsuariosDTO;
    }

    public static EnderecoResponseToUsuarioDTO toDTO(EnderecoUsuario enderecoUsuario) {
        return new EnderecoResponseToUsuarioDTO(
                enderecoUsuario.getId(),
                    UsuarioMapper.toDTO(enderecoUsuario.getUsuario()),
                    EnderecoMapper.toDTO(enderecoUsuario.getEndereco()),
                enderecoUsuario.getIsAtivo()
            );
    }
}
