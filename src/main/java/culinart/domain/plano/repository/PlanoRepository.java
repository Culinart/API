package culinart.domain.plano.repository;

import culinart.domain.plano.Plano;
import culinart.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanoRepository extends JpaRepository<Plano, Integer> {
    Boolean existsByUsuario_Id(int id);

    Optional<Plano> findByUsuario_Id(int id);
}
