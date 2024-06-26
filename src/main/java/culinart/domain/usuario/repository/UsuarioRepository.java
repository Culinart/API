package culinart.domain.usuario.repository;

import culinart.domain.usuario.Usuario;
import culinart.utils.enums.StatusAtivoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByEmail(String email);
    List<Usuario> findByIsAtivoEquals(StatusAtivoEnum statusAtivoEnum);

    @Query("SELECT c.nome, COUNT(pc.preferencia.id) AS total FROM UsuarioPreferencia pc JOIN pc.preferencia c GROUP BY c.nome ORDER BY total DESC")
    List<Object[]> findPreferenciasMaisPresentes();

    @Query("SELECT MONTH(dataCriacao) as mes, COUNT(id) as quantidade " +
            "FROM Usuario " +
            "WHERE YEAR(dataCriacao) = :ano " +
            "GROUP BY MONTH(dataCriacao)")
    List<Map<String, Object>>  countNewUserByMonth(int ano);
}
