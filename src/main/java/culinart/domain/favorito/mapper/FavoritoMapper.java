package culinart.domain.favorito.mapper;

import culinart.domain.favorito.Favorito;
import culinart.domain.favorito.dto.FavoritoExibicaoDTO;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.usuario.dto.mapper.UsuarioMapper;

public class FavoritoMapper {
    public static FavoritoExibicaoDTO toDTO(Favorito favorito) {
        return FavoritoExibicaoDTO.builder()
                .id(favorito.getId())
                .usuario(UsuarioMapper.toDTO(favorito.getUsuario()))
                .receita(ReceitaMapper.toDTO(favorito.getReceita()))
                .build();
    }
}
