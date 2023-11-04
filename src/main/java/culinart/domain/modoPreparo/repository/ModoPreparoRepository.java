package culinart.domain.modoPreparo.repository;

import culinart.domain.modoPreparo.ModoPreparo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModoPreparoRepository extends JpaRepository<ModoPreparo, Integer> {
    Optional<ModoPreparo> findByPasso(String passo);
}
