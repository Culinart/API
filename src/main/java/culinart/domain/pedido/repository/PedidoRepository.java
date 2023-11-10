package culinart.domain.pedido.repository;

import culinart.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query("SELECT p FROM Pedido as p JOIN Plano as pl ON pl.id = planoId.id" +
            " WHERE pl.usuario.id = :userId AND p.status = 'NEXT'")
    Optional<Pedido> acharPorId(Integer userId);



}
