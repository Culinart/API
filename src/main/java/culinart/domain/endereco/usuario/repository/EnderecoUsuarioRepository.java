package culinart.domain.endereco.usuario.repository;

import culinart.domain.endereco.usuario.EnderecoUsuario;
import culinart.utils.enums.StatusAtivoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoUsuarioRepository extends JpaRepository<EnderecoUsuario, Integer> {
        List<EnderecoUsuario> findEnderecoUsuarioByUsuario_IdOrderByIsAtivo(int id);
        Optional<EnderecoUsuario> findEnderecoUsuarioByEndereco_Id(int id);
        boolean existsByEnderecoCepAndEnderecoNumeroAndUsuarioId(String cep, int numero, int idUsuario);
        Optional<EnderecoUsuario> findEnderecoUsuarioAtivoByUsuario_Id(int id);

}
