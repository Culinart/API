package culinart.domain.assinatura.repository;

import culinart.domain.assinatura.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssinaturaRepository extends JpaRepository<Assinatura, Integer> {
    Optional<Assinatura> findByUsuario_Id(Integer id);
}
