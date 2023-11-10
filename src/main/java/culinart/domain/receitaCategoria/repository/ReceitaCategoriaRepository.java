package culinart.domain.receitaCategoria.repository;

import culinart.domain.receita.Receita;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceitaCategoriaRepository extends JpaRepository<ReceitaCategoria, Integer> {
    Optional<ReceitaCategoria> findByReceita(Receita Receita);
}
