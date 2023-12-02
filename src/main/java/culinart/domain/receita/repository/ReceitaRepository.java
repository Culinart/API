package culinart.domain.receita.repository;

import culinart.domain.receita.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
    boolean existsByNome(String nome);
    @Query(value = "SELECT r.* FROM receita r " +
            "INNER JOIN receita_categoria rc ON r.id = rc.receita_id " +
            "WHERE rc.categoria_id = :categoriaId", nativeQuery = true)
    List<Receita> receitasPorCategoria(Integer categoriaId);

}
