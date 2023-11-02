package culinart.domain.receita.modoPreparo.repository;

import culinart.domain.receita.modoPreparo.ModoPreparo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModoPreparoRepository extends JpaRepository<ModoPreparo, Integer> {
}
