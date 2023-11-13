package culinart.service.endereco.usuario;

import culinart.domain.endereco.usuario.EnderecoUsuario;
import culinart.domain.endereco.usuario.dto.EnderecoResponseToUsuarioDTO;
import culinart.domain.endereco.usuario.dto.mapper.EnderecoUsuarioMapper;
import culinart.domain.endereco.usuario.repository.EnderecoUsuarioRepository;
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
        return repository.findEnderecoUsuarioByUsuario_Id(idUsuario);
    }
}
