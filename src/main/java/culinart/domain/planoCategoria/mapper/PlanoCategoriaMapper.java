package culinart.domain.planoCategoria.mapper;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.mapper.CategoriaMapper;
import culinart.domain.categoria.repository.CategoriaRepository;
import culinart.domain.plano.Plano;
import culinart.domain.plano.mapper.PlanoMapper;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.planoCategoria.PlanoCategoria;
import culinart.domain.planoCategoria.dto.PlanoCategoriaCadastro;
import culinart.domain.planoCategoria.dto.PlanoCategoriaExibicaoDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequiredArgsConstructor
public class PlanoCategoriaMapper {

    private final CategoriaRepository categoriaRepository;
    private final PlanoRepository planoRepository;

    public static PlanoCategoriaExibicaoDTO toDTO(PlanoCategoria planoCategoria) {
        return PlanoCategoriaExibicaoDTO.builder()
                .id(planoCategoria.getId())
                .plano(PlanoMapper.toDTO(planoCategoria.getPlano()))
                .categoria(CategoriaMapper.toDTO(planoCategoria.getCategoria()))
                .build();
    }

    public static PlanoCategoria toEntity(Plano plano, Categoria categoria) {
        return PlanoCategoria.builder()
                .plano(plano)
                .categoria(categoria)
                .build();
    }
}
