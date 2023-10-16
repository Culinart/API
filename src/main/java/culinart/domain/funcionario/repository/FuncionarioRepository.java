package culinart.domain.funcionario.repository;

import culinart.domain.funcionario.Funcionario;
import culinart.domain.funcionario.dto.FuncionarioExibicaoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

}
