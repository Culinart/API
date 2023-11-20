package culinart.domain.receitaCategoria.repository;

import culinart.domain.receita.Receita;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReceitaCategoriaRepository extends JpaRepository<ReceitaCategoria, Integer> {
    Optional<ReceitaCategoria> findByReceita(Receita Receita);

    @Query("SELECT rc FROM receita_categoria rc " +
            "JOIN rc.receita r " +
            "LEFT JOIN Favorito f ON r.id = f.receita.id AND f.usuario.id = :usuarioId " +
            "ORDER BY f.id DESC NULLS LAST")
    List<ReceitaCategoria> findReceitaCategoriaOrderedByFavoritos(@Param("usuarioId") int usuarioId);
}
