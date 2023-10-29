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

    public EnderecoResponseToUsuarioDTO mostrarEnderecoUsuarioPorIdUsuario(int idUsuario){
        Optional<EnderecoUsuario> enderecoUsuarioByUsuarioIdOptional = repository.findEnderecoUsuarioByUsuario_Id(idUsuario);
        if(enderecoUsuarioByUsuarioIdOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuario não possui endereço cadastrado!");
        }
        return EnderecoUsuarioMapper.toDTO(enderecoUsuarioByUsuarioIdOptional.get());
    }
}
