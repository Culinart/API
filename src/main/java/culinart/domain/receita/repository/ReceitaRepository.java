package culinart.domain.receita.repository;

import culinart.domain.receita.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
    boolean existsByNome(String nome);
}
