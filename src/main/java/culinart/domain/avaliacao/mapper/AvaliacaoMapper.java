package culinart.domain.avaliacao.mapper;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.avaliacao.dto.AvaliacaoCadastroDTO;
import culinart.domain.avaliacao.dto.AvaliacaoResponseDTO;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.mapper.UsuarioMapper;

public class AvaliacaoMapper {
    public static AvaliacaoResponseDTO toDTO(Avaliacao avaliacao){
        return AvaliacaoResponseDTO.builder()
                .id(avaliacao.getId())
                .nota(avaliacao.getNota())
                .build();
    }

    public static Avaliacao toEntity(AvaliacaoCadastroDTO avaliacaoCadastroDTO, Usuario usuario, Receita receita) {
        return Avaliacao.builder()
                .usuario(usuario)
                .receita(receita)
                .nota(avaliacaoCadastroDTO.getNota())
                .build();
    }
}
