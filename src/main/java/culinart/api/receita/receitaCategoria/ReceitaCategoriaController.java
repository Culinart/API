package culinart.api.receita.receitaCategoria;

import culinart.service.receita.categoria.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receitas/categorias")
@RequiredArgsConstructor
public class ReceitaCategoriaController {
    private final CategoriaService receitaCategoriaService;
}
