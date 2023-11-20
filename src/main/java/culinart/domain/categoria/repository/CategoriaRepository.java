package culinart.domain.categoria.repository;

import culinart.domain.categoria.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Boolean existsByNome(String nome);

    Optional<Categoria> findByNome(String nome);
}
