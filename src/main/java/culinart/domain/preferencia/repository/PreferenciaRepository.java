package culinart.domain.preferencia.repository;

import culinart.domain.preferencia.Preferencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenciaRepository extends JpaRepository<Preferencia, Integer> {
    boolean existsByNome(String nome);
}
