package culinart.domain.receita.imagem.repository;

import culinart.domain.receita.imagem.ImagemReceita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagemReceitaRepository extends JpaRepository<ImagemReceita, Integer> {
    Optional<ImagemReceita> findByReceita_Id(Integer idReceita);
}
