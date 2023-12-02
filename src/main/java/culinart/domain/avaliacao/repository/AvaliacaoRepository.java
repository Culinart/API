package culinart.domain.avaliacao.repository;

import culinart.domain.avaliacao.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {
    @Query(value = "SELECT \n" +
            "    r.nome AS nome_receita, \n" +
            "    COUNT(a.id) AS quantidade_avaliacoes, \n" +
            "    ROUND(AVG(a.nota), 1) AS media_notas\n" +
            "FROM avaliacao a\n" +
            "JOIN receita r ON a.receita_id = r.id\n" +
            "GROUP BY r.nome;", nativeQuery = true)
    List<Object[]> avaliacoesTxt();

    List<Avaliacao> findByReceita_Id(Integer id);
}
