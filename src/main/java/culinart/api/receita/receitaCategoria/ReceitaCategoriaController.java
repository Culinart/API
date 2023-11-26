package culinart.api.receita.receitaCategoria;

import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaCadastroDTO;
import culinart.domain.receitaCategoria.dto.ReceitaCategoriaExibicaoDTO;
import culinart.domain.receitaCategoria.mapper.ReceitaCategoriaMapper;
import culinart.service.receita.receitaCategoria.ReceitaCategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<ReceitaCategoriaExibicaoDTO> cadastrarReceitaCategoria(
            @RequestBody ReceitaCategoriaCadastroDTO receitaCategoria, @RequestParam MultipartFile imagemReceita) throws IOException {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ReceitaCategoriaMapper
                        .toDTO(receitaCategoriaService.cadastrarReceitaCategoria(receitaCategoria, imagemReceita)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletarReceitaCategoria(@PathVariable int id){
        receitaCategoriaService.deletarReceitaCategoria(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<List<ReceitaCategoriaExibicaoDTO>> pesquisarReceitaCategoria(@RequestParam String parametro){
        List<ReceitaCategoriaExibicaoDTO> collect = receitaCategoriaService.pesquisarReceitaCategoria(parametro)
                .stream().map(ReceitaCategoriaMapper::toDTO).collect(Collectors.toList());

        if(collect.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(collect);
    }

    @GetMapping("/receitas/{id}/imagem")
    public ResponseEntity<byte[]> getImagem(@PathVariable int id){
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .body(receitaCategoriaService.getImagem(id));
    }
}
