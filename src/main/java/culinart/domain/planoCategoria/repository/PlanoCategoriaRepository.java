package culinart.domain.planoCategoria.repository;

import culinart.domain.planoCategoria.PlanoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanoCategoriaRepository extends JpaRepository<PlanoCategoria, Integer> {
    List<PlanoCategoria> findByPlano_Id(int planoId);
}
