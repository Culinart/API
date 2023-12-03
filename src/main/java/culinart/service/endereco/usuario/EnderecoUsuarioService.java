package culinart.service.endereco.usuario;

import culinart.domain.endereco.usuario.EnderecoUsuario;
import culinart.domain.endereco.usuario.dto.EnderecoResponseToUsuarioDTO;
import culinart.domain.endereco.usuario.dto.mapper.EnderecoUsuarioMapper;
import culinart.domain.endereco.usuario.repository.EnderecoUsuarioRepository;
import culinart.utils.enums.StatusAtivoEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoUsuarioService {
    private final EnderecoUsuarioRepository repository;

    public EnderecoUsuarioService(EnderecoUsuarioRepository repository) {
        this.repository = repository;
    }

    public List<EnderecoResponseToUsuarioDTO> mostrarTodosEnderecosUsuarios(){
        return EnderecoUsuarioMapper.toDTOList(repository.findAll());
    }

    public List<EnderecoUsuario> mostrarEnderecoUsuarioPorIdUsuario(int idUsuario){
        return repository.findEnderecoUsuarioByUsuario_IdOrderByIsAtivo(idUsuario);
    }

    public EnderecoUsuario ativarEnderecoUsuario(int idEnderecoUsuario, int idUsuario) {

        List<EnderecoUsuario> enderecos = repository.findEnderecoUsuarioByUsuario_IdOrderByIsAtivo(idUsuario);

        for (EnderecoUsuario enderecoUsuario : enderecos) {
            if (enderecoUsuario.getId() == idEnderecoUsuario) {
                enderecoUsuario.setIsAtivo(StatusAtivoEnum.ATIVO);
            } else {
                enderecoUsuario.setIsAtivo(StatusAtivoEnum.INATIVO);
            }
            repository.save(enderecoUsuario);
        }

        return repository.findById(idEnderecoUsuario).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereco Usuario não encontrado para atualização"));
    }

    public Optional<EnderecoUsuario> findEnderecoAtivoByUsuarioId(int idUsuario) {
        return repository.findEnderecoUsuarioAtivo(StatusAtivoEnum.ATIVO, idUsuario);
    }

}
