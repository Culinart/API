package culinart.domain.preferencia.repository;

import culinart.domain.preferencia.Preferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferenciaRepository extends JpaRepository<Preferencia, Integer> {
    boolean existsByNome(String nome);
    Optional<Preferencia> findByNome(String nome);
}
