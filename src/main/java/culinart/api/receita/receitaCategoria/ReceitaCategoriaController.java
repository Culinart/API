package culinart.api.receita.receitaCategoria;

import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaExibicaoDTO;
import culinart.domain.receitaCategoria.mapper.ReceitaCategoriaMapper;
import culinart.service.receita.receitaCategoria.ReceitaCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/receitas/categorias")
@RequiredArgsConstructor
public class ReceitaCategoriaController {
    //Services
    private final ReceitaCategoriaService receitaCategoriaService;


    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<ReceitaCategoriaExibicaoDTO>> exibirTodasReceitasCategorias(@PathVariable int idUsuario){
        List<ReceitaCategoria> receitaCategorias = receitaCategoriaService.exibirTodasReceitasCategorias(idUsuario);
        if (receitaCategorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReceitaCategoriaExibicaoDTO> receitaCategoriaExibicaoDTOS = new ArrayList<>();
        for (ReceitaCategoria receitaCategoria : receitaCategorias) {
            receitaCategoriaExibicaoDTOS.add(ReceitaCategoriaMapper.toDTO(receitaCategoria));
        }

        return ResponseEntity.ok(receitaCategoriaExibicaoDTOS);
    }

    @PostMapping
    public ResponseEntity<ReceitaCategoriaExibicaoDTO> cadastrarReceitaCategoria(@RequestBody ReceitaCategoria receitaCategoria){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReceitaCategoriaMapper
                        .toDTO(receitaCategoriaService.cadastrarReceitaCategoria(receitaCategoria)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletarReceitaCategoria(@PathVariable int id){
        receitaCategoriaService.deletarReceitaCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
