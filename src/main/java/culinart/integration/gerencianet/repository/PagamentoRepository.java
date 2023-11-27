package culinart.integration.gerencianet.repository;

import culinart.domain.pagamento.Pagamento;
import culinart.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    Optional<Pagamento> findByUsuarioOrderByDataExpiracaoDesc(Usuario usuario);
}
