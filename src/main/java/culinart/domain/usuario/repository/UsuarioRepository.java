package culinart.domain.usuario.repository;

import culinart.domain.usuario.Usuario;
import culinart.utils.enums.StatusAtivoEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<Usuario> findByIsAtivoEquals(StatusAtivoEnum statusAtivoEnum);
}
