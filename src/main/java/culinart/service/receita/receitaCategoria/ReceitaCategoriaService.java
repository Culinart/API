package culinart.service.receita.receitaCategoria;

import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaCategoriaService {
    private final ReceitaCategoriaRepository repository;

    public List<ReceitaCategoria> exibirTodasReceitasCategorias() {
        return repository.findAll();
    }

    public ReceitaCategoria cadastrarReceitaCategoria(ReceitaCategoria receitaCategoria) {
        if (repository.findByReceita(receitaCategoria.getReceita()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita com categorias já cadastrada");
        }

        return repository.save(receitaCategoria);
    }
}
