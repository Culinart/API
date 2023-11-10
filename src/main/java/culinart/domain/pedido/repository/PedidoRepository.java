package culinart.domain.pedido.repository;

import culinart.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("SELECT p FROM Pedido as p JOIN Plano as pl ON pl.id = plano.id" +
            " WHERE pl.usuario.id = :userId AND p.status = 'NEXT'")
    Optional<Pedido> acharProximoPedidoUser(Integer userId);
    @Modifying
    @Query("UPDATE Pedido p SET p.status = 'CANCELADO' WHERE p.id = :pedido_id")
    void pularEntrega(Integer pedido_id);

}
