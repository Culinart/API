package culinart.domain.endereco.repository;

import culinart.domain.endereco.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    List<Endereco> findByCep(String cep);

    @Transactional
    @Query("SELECT e FROM Endereco e WHERE e.cep = :cep AND e.numero = :numero")
    List<Endereco> findByCepAndNumero(String cep, int numero);
}