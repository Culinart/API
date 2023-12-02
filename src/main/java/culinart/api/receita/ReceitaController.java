package culinart.api.receita;

import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaCadastroDTO;
import culinart.domain.receita.dto.ReceitaExibicaoDTO;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.service.receita.ReceitaService;
import culinart.service.receita.receitaCategoria.ReceitaCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/receitas")
@RequiredArgsConstructor
public class ReceitaController {
    private final ReceitaService receitaService;
    private final ReceitaCategoriaService receitaCategoriaService;

    @GetMapping
    public ResponseEntity<List<ReceitaExibicaoDTO>> exibirTodasReceitas() {
        List<Receita> receitas = receitaService.exibirTodasReceitas();
        if (receitas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<ReceitaExibicaoDTO> receitaExibicaoDTOS = new ArrayList<>();
        for (Receita receita : receitas) {
            receitaExibicaoDTOS.add(ReceitaMapper.toDTO(receita));
        }

        return ResponseEntity.ok(receitaExibicaoDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaExibicaoDTO> exibirReceitaPorId(@PathVariable int id){
        return ResponseEntity.ok(ReceitaMapper.toDTO(receitaService.exibirReceitaPorId(id)));
    }

    @PostMapping
    public ResponseEntity<ReceitaExibicaoDTO> cadastrarReceita(@RequestBody ReceitaCadastroDTO receita){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReceitaMapper.toDTO(receitaService.cadastrarReceita(receita)));
    };

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaExibicaoDTO> atualizarReceita(@PathVariable int id,@RequestBody ReceitaCadastroDTO receita){
        return ResponseEntity.ok(ReceitaMapper.toDTO(receitaService.atualizarReceita(id,receita)));
    };

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarReceita(@PathVariable int id){
        receitaService.deletarReceita(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
