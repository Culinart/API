package culinart.domain.pedido.repository;

import culinart.domain.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    @Query(value = "SELECT\n" +
            "    p.id AS pedido_id,\n" +
            "    p.valor,\n" +
            "    p.data_entrega AS data_entrega,\n" +
            "    p.status,\n" +
            "    e.logradouro,\n" +
            "    e.numero,\n" +
            "    r.id AS receita_id,\n" +
            "    r.nome AS nome_receita,\n" +
            "    r.horas,\n" +
            "    r.minutos,\n" +
            "    r.qtd_porcoes,\n" +
            "    r.imagem,\n" +
            "    GROUP_CONCAT(DISTINCT pref.nome) AS preferencias_nome,\n" +
            "    GROUP_CONCAT(DISTINCT pref.cor_fundo) AS cor_fundo,\n" +
            "    GROUP_CONCAT(DISTINCT pref.cor_texto) AS cor_texto,\n" +
            "    GROUP_CONCAT(DISTINCT c.nome) as categorias,\n" +
            "    COALESCE(av.media_notas, 0) AS media_notas,\n" +
            "    COALESCE(av.quantidade_notas, 0) AS quantidade_notas\n" +
            "FROM pedido p\n" +
            "JOIN pedido_receita pr ON p.id = pr.pedido_id\n" +
            "JOIN receita r ON pr.receita_id = r.id\n" +
            "JOIN receita_categoria rc ON r.id = rc.receita_id\n" +
            "JOIN categoria c ON rc.categoria_id = c.id\n" +
            "JOIN receita_preferencia rp ON r.id = rp.receita_id\n" +
            "JOIN preferencia pref ON rp.preferencia_id = pref.id\n" +
            "JOIN plano pl ON p.plano_id = pl.id\n" +
            "JOIN endereco e ON p.endereco_id = e.id\n" +
            "LEFT JOIN (\n" +
            "    SELECT receita_id, AVG(nota) AS media_notas, COUNT(id) AS quantidade_notas\n" +
            "    FROM avaliacao\n" +
            "    GROUP BY receita_id\n" +
            ") av ON r.id = av.receita_id\n" +
            "WHERE p.data_entrega = :dataEntrega AND pl.usuario_id = :idUser\n" +
            "GROUP BY pedido_id, receita_id;", nativeQuery = true)
    List<Object[]> acharProximoPedidoUser(Integer idUser, LocalDate dataEntrega);
    List<Pedido> findAllByPlanoUsuarioId(Integer usuarioId);
    @Modifying
    @Query("UPDATE Pedido p SET p.status = 'CANCELADO' WHERE p.id = :pedido_id")
    void pularEntrega(Integer pedido_id);

    @Modifying
    @Query("UPDATE Pedido p SET p.status = 'ENTREGUE' WHERE p.id = :pedidoId")
    void atualizarStatusParaEntregue(Integer pedidoId);

    @Query(value = "SELECT \n" +
            "    pedidos_agrupados.pedido_id,\n" +
            "    pedidos_agrupados.data_entrega,\n" +
            "    pedidos_agrupados.nome_usuario,\n" +
            "    pedidos_agrupados.logradouro,\n" +
            "    pedidos_agrupados.numero,\n" +
            "GROUP_CONCAT(DISTINCT receitas.nome) AS receitas, \n" +
            "GROUP_CONCAT(DISTINCT categorias.nome) AS categorias,\n" +
            "COUNT(DISTINCT receitas.id) AS quantidade_de_receitas,\n" +
            "pedidos_agrupados.quantidade_porcoes\n" +
            "FROM (\n" +
            "    SELECT \n" +
            "        p.id AS pedido_id,\n" +
            "        p.data_entrega,\n" +
            "        u.nome AS nome_usuario,\n" +
            "        e.logradouro,\n" +
            "        e.numero,\n" +
            "        SUM(r.qtd_porcoes) AS quantidade_porcoes\n" +
            "    FROM pedido p\n" +
            "    JOIN plano pl ON p.plano_id = pl.id\n" +
            "    JOIN usuario u ON pl.usuario_id = u.id\n" +
            "    JOIN endereco_usuario eu ON u.id = eu.usuario_id\n" +
            "    JOIN endereco e ON e.id = eu.endereco_id \n" +
            "    JOIN pedido_receita pr ON pr.pedido_id = p.id\n" +
            "    JOIN receita r ON r.id = pr.receita_id\n" +
            "    WHERE p.data_entrega AND p.status = 'ATIVO' \n" +
            "    AND eu.is_ativo = 'ATIVO'\n" +
            "    GROUP BY p.id, p.data_entrega, u.nome, e.logradouro, e.numero\n" +
            ") AS pedidos_agrupados\n" +
            "JOIN pedido_receita pr ON pedidos_agrupados.pedido_id = pr.pedido_id\n" +
            "JOIN receita receitas ON pr.receita_id = receitas.id\n" +
            "JOIN receita_categoria rc ON rc.receita_id = receitas.id\n" +
            "JOIN categoria categorias ON rc.categoria_id = categorias.id\n" +
            "GROUP BY pedidos_agrupados.pedido_id, pedidos_agrupados.data_entrega, pedidos_agrupados.nome_usuario, pedidos_agrupados.logradouro, pedidos_agrupados.numero, pedidos_agrupados.quantidade_porcoes;", nativeQuery = true)
    List<Object[]> findProximosPedidos(LocalDate dataLimite);

    @Modifying
    @Query(value = "DELETE FROM pedido_receita pr WHERE pr.receita_id = :idReceita AND pr.pedido_id = :idPedido", nativeQuery = true)
    void deleteReceitaPedido(@Param("idPedido") Integer idPedido, @Param("idReceita") Integer idReceita);





    @Modifying
    @Query(value = "INSERT INTO pedido_receita(pedido_id, receita_id) VALUES (:idPedido, :idReceita)", nativeQuery = true)
    void addReceitaPedido(@Param("idPedido") int idPedido, @Param("idReceita") int idReceita);

}
