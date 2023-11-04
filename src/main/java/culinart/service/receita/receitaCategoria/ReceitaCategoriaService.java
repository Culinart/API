package culinart.service.receita.receitaCategoria;

import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReceitaCategoriaService {
    private final ReceitaCategoriaRepository repository;
}
