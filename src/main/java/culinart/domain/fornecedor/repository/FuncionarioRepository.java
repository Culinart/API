package culinart.domain.fornecedor.repository;

import culinart.domain.fornecedor.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
