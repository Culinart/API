package culinart.api.receita.receitaCategoria;

import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaExibicaoDTO;
import culinart.domain.receitaCategoria.mapper.ReceitaCategoriaMapper;
import culinart.service.receita.categoria.CategoriaService;
import culinart.service.receita.ingrediente.IngredienteService;
import culinart.service.receita.modoPreparo.ModoPreparoService;
import culinart.service.receita.preferencia.PreferenciaService;
import culinart.service.receita.receitaCategoria.ReceitaCategoriaService;
import lombok.RequiredArgsConstructor;
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
    private final IngredienteService ingredienteService;
    private final ModoPreparoService modoPreparoService;
    private final PreferenciaService preferenciaService;
    private final CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<ReceitaCategoriaExibicaoDTO>> exibirTodasReceitasCategorias(){
        List<ReceitaCategoria> receitaCategorias = receitaCategoriaService.exibirTodasReceitasCategorias();
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
    public ReceitaCategoria cadastrarReceitaCategoria(@RequestBody ReceitaCategoria receitaCategoria){
        return receitaCategoria;
    }
}
