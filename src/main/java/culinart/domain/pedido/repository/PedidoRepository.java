package culinart.domain.pedido.repository;

import culinart.domain.pedido.Pedido;
import culinart.domain.pedido.dto.DatasPedidosDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("SELECT p FROM Pedido as p JOIN Plano as pl ON pl.id = plano.id" +
            " WHERE pl.usuario.id = :userId AND  p.dataEntrega = :dataEntrega")
    Optional<Pedido> acharProximoPedidoUser(Integer userId, LocalDate dataEntrega);
    @Modifying
    @Query("UPDATE Pedido p SET p.status = 'CANCELADO' WHERE p.id = :pedido_id")
    void pularEntrega(Integer pedido_id);

    List<Pedido> findAllByUsuarioId(Integer usuarioId);
}
