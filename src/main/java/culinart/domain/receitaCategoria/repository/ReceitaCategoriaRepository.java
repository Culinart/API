package culinart.domain.receitaCategoria.repository;

import culinart.domain.receita.Receita;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReceitaCategoriaRepository extends JpaRepository<ReceitaCategoria, Integer> {
    Optional<ReceitaCategoria> findByReceita(Receita Receita);

    List<ReceitaCategoria> findByReceita_Id(Integer id);

//    @Query("SELECT rc FROM ReceitaCategoria rc " +
//            "JOIN rc.receita r " +
//            "LEFT JOIN rc.categoria c " +
//            "LEFT JOIN rc.preferencia p " +
//            "WHERE LOWER(r.nome) LIKE %:parametro% " +
//            "   OR LOWER(r.descricao) LIKE %:parametro% " +
//            "   OR LOWER(c.nome) LIKE %:parametro% " +
//            "   OR LOWER(p.nome) LIKE %:parametro%")
//    List<ReceitaCategoria> findByParametro(@Param("parametro") String parametro);
//
//    @Query("SELECT rc FROM ReceitaCategoria rc " +
//            "JOIN rc.receita r " +
//            "LEFT JOIN Favorito f ON r.id = f.receita.id AND f.usuario.id = :usuarioId " +
//            "ORDER BY f.id DESC NULLS LAST")
//    List<ReceitaCategoria> findReceitaCategoriaOrderedByFavoritos(@Param("usuarioId") int usuarioId);

}
