package culinart.service.receita.ingrediente;

import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.ingrediente.repository.IngredienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngredienteService {

    private final IngredienteRepository ingredienteRepository;


    public List<Ingrediente> exibirTodosIngredientes() {
        return ingredienteRepository.findAll();
    }

    public Ingrediente exibirIngredientesPorId(int id) {
        return ingredienteRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado"));
    }

    public Ingrediente cadastrarIngrediente(Ingrediente ingrediente) {
        return ingredienteRepository.save(ingrediente);
    }

    public Ingrediente atualizarIngrediente(int id, Ingrediente ingrediente) {
        if (ingredienteRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado");
        }

        ingrediente.setId(id);
        return ingredienteRepository.save(ingrediente);
    }

    public void deletarIngrediete(int id) {
        if (ingredienteRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ingrediente não encontrado");
        }

        ingredienteRepository.delete(ingredienteRepository.findById(id).get());
    }

    public List<Ingrediente> saveAll(List<Ingrediente> ingredientes) {
        return ingredienteRepository.saveAll(ingredientes);
    }
}
