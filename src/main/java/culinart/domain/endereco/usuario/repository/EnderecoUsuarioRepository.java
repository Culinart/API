package culinart.domain.endereco.usuario.repository;

import culinart.domain.endereco.usuario.EnderecoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, Integer> {
        Optional<EnderecoUsuario> findEnderecoUsuarioByUsuario_Id(int id);
        Optional<EnderecoUsuario> findEnderecoUsuarioByEndereco_Id(int id);
        boolean existsByEnderecoCepAndEnderecoNumeroAndUsuarioId(String cep, int numero, int idUsuario);
}
