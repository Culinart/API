package culinart.domain.receitaPreferencia.repository;

import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaPreferencia.ReceitaPreferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceitaPreferenciaRepository extends JpaRepository<ReceitaPreferencia, Integer> {
    List<ReceitaPreferencia> findByReceita_Id(Integer id);
}
