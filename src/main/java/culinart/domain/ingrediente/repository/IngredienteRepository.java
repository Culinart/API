package culinart.domain.ingrediente.repository;

import culinart.domain.ingrediente.Ingrediente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Integer> {
    List<Ingrediente> findByReceita_Id(Integer id);
}
