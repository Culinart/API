package culinart.service.receita.receitaCategoria;

import culinart.api.receita.ReceitaController;
import culinart.api.receita.categoria.CategoriaController;
import culinart.api.receita.ingrediente.IngredienteController;
import culinart.api.receita.modoPreparo.ModoPreparoController;
import culinart.api.receita.preferencia.PreferenciaController;
import culinart.domain.categoria.Categoria;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.receita.Receita;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaCategoria.repository.ReceitaCategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceitaCategoriaService {

    private final ReceitaCategoriaRepository repository;
    private final IngredienteController ingredienteController;
    private final ModoPreparoController modoPreparoController;
    private final PreferenciaController preferenciaController;
    private final CategoriaController categoriaController;
    private final ReceitaController receitaController;

    public List<ReceitaCategoria> exibirTodasReceitasCategorias(int idUsuario) {
        return repository.findReceitaCategoriaOrderedByFavoritos(idUsuario);
    }

    public ReceitaCategoria cadastrarReceitaCategoria(ReceitaCategoria receitaCategoria) {
        List<Ingrediente> ingredientes = receitaCategoria.getReceita().getIngredientes();
        List<ModoPreparo> modoPreparos = receitaCategoria.getReceita().getModoPreparos();
        List<Categoria> categorias = receitaCategoria.getCategoria();
        List<Preferencia> preferencias = receitaCategoria.getPreferencia();
        Receita receita = receitaCategoria.getReceita();

        ingredientes.forEach(ingredienteController::cadastarIngrediente);
        modoPreparos.forEach(modoPreparoController::cadastrarModoPreparo);
        categorias.forEach(categoriaController::cadastarCategoria);
        preferencias.forEach(preferenciaController::cadastrarPreferencia);
        receitaController.cadastrarReceita(receita);

        return repository.save(receitaCategoria);
    }

    public void deletarReceitaCategoria(int idReceitaCategoria){
        ReceitaCategoria receitaCategoria = repository.findById(idReceitaCategoria).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "ReceitaCategoria não encontrada"));
        repository.delete(receitaCategoria);
    }
}
