package culinart.service.categoria;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<Categoria> exibirTodosCategoria() {
        return categoriaRepository.findAll();
    }

    public Categoria exibirCategoriaPorId(int id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
    }

    public Categoria cadastrarCategoria(Categoria categoria) {
        if (categoriaRepository.existsByNome(categoria.getNome())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria já cadastrada");
        }

        return categoriaRepository.save(categoria);
    }

    public Categoria atualizarCategoria(int id, Categoria categoria) {
        if (categoriaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        }
        categoria.setId(id);
        return categoriaRepository.save(categoria);
    }

    public void deletarCategoria(int id) {
        if (categoriaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        }
        categoriaRepository.delete(categoriaRepository.findById(id).get());
    }
}
