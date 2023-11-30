package culinart.service.plano.categoria;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.repository.CategoriaRepository;
import culinart.domain.plano.Plano;
import culinart.domain.plano.repository.PlanoRepository;
import culinart.domain.planoCategoria.PlanoCategoria;
import culinart.domain.planoCategoria.dto.CategoriaId;
import culinart.domain.planoCategoria.dto.PlanoCategoriaCadastro;
import culinart.domain.planoCategoria.repository.PlanoCategoriaRepository;
import culinart.service.pedido.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanoCategoriaService {
    private final PlanoCategoriaRepository planoCategoriaRepository;
    private final PlanoRepository planoRepository;
    private final CategoriaRepository categoriaRepository;
//    private final PedidoService pedidoService;

    public List<PlanoCategoria> exibirPlanoCategorias() {
        return planoCategoriaRepository.findAll();
    }

    // Update the PlanoCategoriaService class

    public List<PlanoCategoria> exibirPlanoCategoriaPorUserId(int userId) {
        Plano plano = planoRepository.findByUsuario_Id(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado para o usuário"));

        return planoCategoriaRepository.findByPlano_Id(plano.getId());
    }

    public List<PlanoCategoria> cadastrarPlanoCategoria(PlanoCategoriaCadastro planoCategoriaCadastro) {
        Plano plano = null;
        for (int i = 0; i < planoCategoriaCadastro.getCategoriaId().size(); i++){
            plano = planoRepository.findById(planoCategoriaCadastro.getPlanoId()).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano não encontrado"));

            Integer categoriaId = planoCategoriaCadastro.getCategoriaId().get(i);
            Categoria categoria = categoriaRepository.findById(categoriaId).orElseThrow(() ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));

            PlanoCategoria novoPlanoCategoria = new PlanoCategoria();
            novoPlanoCategoria.setPlano(plano);
            novoPlanoCategoria.setCategoria(categoria);
            planoCategoriaRepository.save(novoPlanoCategoria);
        }
        List<PlanoCategoria> listaCategorias = planoCategoriaRepository.findByPlano_Id(planoCategoriaCadastro.getPlanoId());
        return listaCategorias;
    }

    public void deletarPlanoCategoria(int id) {
        PlanoCategoria planoCategoria = planoCategoriaRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada"));
        planoCategoriaRepository.delete(planoCategoria);
    }
}
