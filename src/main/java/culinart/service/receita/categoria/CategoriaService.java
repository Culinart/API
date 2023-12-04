package culinart.service.receita.categoria;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.dto.CategoriaCadastroDTO;
import culinart.domain.categoria.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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

    public Categoria cadastrarCategoria(CategoriaCadastroDTO cadastroDTO) {
        Categoria categoria = categoriaRepository.findById(cadastroDTO.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> atualizarCategoria(List<Categoria> categorias) {
        List<Categoria>listaCategoriasAtt = new ArrayList<>();
        for (Categoria categoriaDaVez:categorias) {
            categoriaRepository.save(categoriaDaVez);
            listaCategoriasAtt.add(categoriaDaVez);
        }
        return listaCategoriasAtt;
    }

    public void deletarCategoria(int id) {
        if (categoriaRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
        }
        categoriaRepository.delete(categoriaRepository.findById(id).get());
    }
}
