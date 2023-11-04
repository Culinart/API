package culinart.api.receita.ingrediente;

import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.ingrediente.dto.IngredienteExibicaoDTO;
import culinart.domain.ingrediente.dto.mapper.IngredienteMapper;
import culinart.service.receita.ingrediente.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ingredientes")
@RequiredArgsConstructor
public class IngredienteController {
    private final IngredienteService ingredienteService;

    @GetMapping
    public ResponseEntity<List<IngredienteExibicaoDTO>> exibirTodosIngredientes(){
        List<Ingrediente> ingredientes = ingredienteService.exibirTodosIngredientes();
        if (ingredientes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<IngredienteExibicaoDTO> ingredienteExibicaoDTOS = new ArrayList<>();
        for (Ingrediente ingrediente : ingredientes) {
            ingredienteExibicaoDTOS.add(IngredienteMapper.toDTO(ingrediente));
        }

        return ResponseEntity.ok(ingredienteExibicaoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteExibicaoDTO> exibirIngredientePorId(@PathVariable int id){
        return ResponseEntity.ok(IngredienteMapper.toDTO(ingredienteService.exibirIngredientesPorId(id)));
    }

    @PostMapping
    public ResponseEntity<IngredienteExibicaoDTO> cadastarIngrediente(@RequestBody Ingrediente ingrediente){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(IngredienteMapper.toDTO(ingredienteService.cadastrarIngrediente(ingrediente)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteExibicaoDTO> atualizarIngrediente(@PathVariable int id, @RequestBody Ingrediente ingrediente){
        return ResponseEntity.ok(IngredienteMapper.toDTO(ingredienteService.atualizarIngrediente(id,ingrediente)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarIngrediente(@PathVariable int id){
        ingredienteService.deletarIngrediete(id);
        return ResponseEntity.noContent().build();
    }
}
