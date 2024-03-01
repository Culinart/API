package culinart.domain.assinatura.repository;

import culinart.domain.assinatura.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Integer> {
}
