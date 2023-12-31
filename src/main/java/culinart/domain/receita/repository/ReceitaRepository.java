package culinart.domain.receita.repository;

import culinart.domain.receita.Receita;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReceitaRepository extends JpaRepository<Receita, Integer>, JpaSpecificationExecutor<Receita> {
    boolean existsByNome(String nome);
    @Query(value = "SELECT r.* FROM receita r " +
            "INNER JOIN receita_categoria rc ON r.id = rc.receita_id " +
            "WHERE rc.categoria_id = :categoriaId", nativeQuery = true)
    List<Receita> receitasPorCategoria(Integer categoriaId);

    List<Receita> findAll(Specification<Receita> spec);
}
