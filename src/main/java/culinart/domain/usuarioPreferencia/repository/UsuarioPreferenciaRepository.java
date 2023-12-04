package culinart.domain.usuarioPreferencia.repository;

import culinart.domain.usuarioPreferencia.UsuarioPreferencia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioPreferenciaRepository extends JpaRepository<UsuarioPreferencia, Integer> {
    List<UsuarioPreferencia> findByUsuario_Id(Integer integer);
    void deleteByPreferenciaContains(String nome);
}
