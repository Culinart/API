package culinart.api.plano.categoria;

import culinart.domain.plano.mapper.PlanoMapper;
import culinart.domain.planoCategoria.PlanoCategoria;
import culinart.domain.planoCategoria.dto.PlanoCategoriaCadastro;
import culinart.domain.planoCategoria.dto.PlanoCategoriaExibicaoDTO;
import culinart.domain.planoCategoria.mapper.PlanoCategoriaMapper;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaExibicaoDTO;
import culinart.domain.receita.dto.mapper.ReceitaMapper;
import culinart.service.pedido.PedidoService;
import culinart.service.plano.categoria.PlanoCategoriaService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/planos/categorias")
@RequiredArgsConstructor
public class PlanoCategoriaController {
    private final PlanoCategoriaService planoCategoriaService;
    private final PedidoService pedidoService;
    @GetMapping
    public ResponseEntity<List<PlanoCategoriaExibicaoDTO>> exibirPlanoCategorias(){
        List<PlanoCategoria> planoCategorias = planoCategoriaService.exibirPlanoCategorias();
        if (planoCategorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PlanoCategoriaExibicaoDTO>  planoCategoriaExibicaoDTOS = new ArrayList<>();
        for (PlanoCategoria planoCategoria : planoCategorias) {
            planoCategoriaExibicaoDTOS.add(PlanoCategoriaMapper.toDTO(planoCategoria));
        }

        return ResponseEntity.ok(planoCategoriaExibicaoDTOS);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PlanoCategoriaExibicaoDTO>> exibirPlanoCategoriaPorUserId(@PathVariable int userId) {
        List<PlanoCategoria> planoCategorias = planoCategoriaService.exibirPlanoCategoriaPorUserId(userId);

        if (planoCategorias.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<PlanoCategoriaExibicaoDTO> planoCategoriaExibicaoDTOS = new ArrayList<>();
        for (PlanoCategoria planoCategoria : planoCategorias) {
            planoCategoriaExibicaoDTOS.add(PlanoCategoriaMapper.toDTO(planoCategoria));
        }

        return ResponseEntity.ok(planoCategoriaExibicaoDTOS);
    }


    @PostMapping
    public ResponseEntity<List<PlanoCategoriaExibicaoDTO>> cadastrarPlanoCategoria(@RequestBody PlanoCategoriaCadastro planoCategoriaCadastro){
        List <PlanoCategoria> listaCategorias = planoCategoriaService.cadastrarPlanoCategoria(planoCategoriaCadastro);
        pedidoService.criarPedido(listaCategorias.get(0).getPlano().getUsuario().getId(), listaCategorias.get(0).getPlano(), "Plano", null);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(listaCategorias.stream()
                        .map(PlanoCategoriaMapper::toDTO).toList());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPlanoCategoria(@PathVariable int id){
        planoCategoriaService.deletarPlanoCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
