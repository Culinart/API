package culinart.api.categoria;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.dto.CategoriaExibicaoDTO;
import culinart.domain.categoria.mapper.CategoriaMapper;
import culinart.domain.receita.ingrediente.Ingrediente;
import culinart.domain.receita.ingrediente.dto.IngredienteExibicaoDTO;
import culinart.domain.receita.ingrediente.dto.mapper.IngredienteMapper;
import culinart.service.categoria.CategoriaService;
import culinart.service.receita.ingrediente.IngredienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {
    private final CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaExibicaoDTO>> exibirTodosCategoria(){
        List<Categoria> categorias = categoriaService.exibirTodosCategoria();
        if (categorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<CategoriaExibicaoDTO> categoriaExibicaoDTOS = new ArrayList<>();
        for (Categoria categoria : categorias) {
            categoriaExibicaoDTOS.add(CategoriaMapper.toDTO(categoria));
        }

        return ResponseEntity.ok(categoriaExibicaoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaExibicaoDTO> exibirCategoriaPorId(@PathVariable int id){
        return ResponseEntity.ok(CategoriaMapper.toDTO(categoriaService.exibirCategoriaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<CategoriaExibicaoDTO> cadastarCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CategoriaMapper.toDTO(categoriaService.cadastrarCategoria(categoria)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaExibicaoDTO> atualizarCategoria(@PathVariable int id, @RequestBody Categoria categoria){
        return ResponseEntity.ok(CategoriaMapper.toDTO(categoriaService.atualizarCategoria(id,categoria)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable int id){
        categoriaService.deletarCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
