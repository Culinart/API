package culinart.service.usuario;

import culinart.domain.usuario.Endereco;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.dto.EnderecoExibicaoDTO;
import culinart.domain.usuario.mapper.EnderecoMapper;
import culinart.domain.usuario.repository.EnderecoRepository;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.integration.ViaCep.client.ViaCepClient;
import culinart.integration.ViaCep.dto.ViaCepResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ViaCepClient viaCepClient;

    public EnderecoService(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, ViaCepClient viaCepClient) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.viaCepClient = viaCepClient;
    }

    public List<EnderecoExibicaoDTO> listarTodosOsEnderecos() {
        List<EnderecoExibicaoDTO> lista = new ArrayList<>();
        for (Endereco endereco : enderecoRepository.findAll()) {
            lista.add(EnderecoMapper.of(endereco));
        }
        return lista;
    }

    public EnderecoExibicaoDTO cadastrarEnderecoAoUsuarioPorId(String cep, Long idUsuario) {
        Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }

        Endereco endereco = getEnderecoPeloCep(cep);

        if (usuario.get().getEndereco().equals(null)) {
            usuario.get().setEndereco(endereco);
            usuarioRepository.save(usuario.get());
        } else {
            throw new IllegalArgumentException("Usuario já possui endereço cadastrado");
        }
        enderecoRepository.save(endereco);
        return EnderecoMapper.of(endereco);
    }

    public Endereco getEnderecoPeloCep(String cep) {
        ViaCepResponse response = this.viaCepClient.getCEP(cep);
        return EnderecoMapper.of(response);
    }

    public Object atualizarEndereco() {
        return null;
    }
}
