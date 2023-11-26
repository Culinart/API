package culinart.domain.favorito.repository;

import culinart.domain.favorito.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
    boolean existsByUsuario_IdAndReceita_Id(int idUsuario,int idReceita);
}
