package culinart.service.endereco;

import culinart.domain.endereco.Endereco;
import culinart.domain.endereco.dto.EnderecoExibicaoDTO;
import culinart.domain.endereco.dto.mapper.EnderecoMapper;
import culinart.domain.endereco.repository.EnderecoRepository;
import culinart.domain.endereco.usuario.EnderecoUsuario;
import culinart.domain.endereco.usuario.dto.mapper.EnderecoUsuarioMapper;
import culinart.domain.endereco.usuario.repository.EnderecoUsuarioRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.integration.ViaCep.client.ViaCepClient;
import culinart.integration.ViaCep.dto.ViaCepResponse;
import culinart.utils.StringUtils;
import culinart.utils.enums.StatusAtivoEnum;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoUsuarioRepository enderecosUsuarioRepository;
    private final ViaCepClient viaCepClient;

    public EnderecoService(EnderecoRepository enderecoRepository, UsuarioRepository usuarioRepository, EnderecoUsuarioRepository enderecosUsuarioRepository, ViaCepClient viaCepClient) {
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.enderecosUsuarioRepository = enderecosUsuarioRepository;
        this.viaCepClient = viaCepClient;
    }

    public List<EnderecoExibicaoDTO> listarTodosOsEnderecos() {
        return EnderecoMapper.toEnderecoDTO(this.enderecoRepository.findAll());
    }

    public Endereco cadastrarEndereco(String cep, String complemento, int numero, int idUsuario) {
        Endereco endereco = getEnderecoPeloCep(cep);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        enderecoRepository.save(endereco);
        return endereco;
    }

    public EnderecoExibicaoDTO atualizarEnderecoDoUsuarioPorId(int idEndereco, Endereco enderecoNovo) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(idEndereco);
        if (enderecoOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }
        enderecoNovo.setId(idEndereco);
        enderecoRepository.save(enderecoNovo);
        return EnderecoMapper.toDTO(enderecoNovo);
    }


    public void deletarEnderecoDoUsuarioPorId(int idEndereco) {
        Optional<EnderecoUsuario> enderecoUsuarioOptional = enderecosUsuarioRepository.findEnderecoUsuarioByEndereco_Id(idEndereco);
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(idEndereco);
        if (enderecoOptional.isEmpty() && enderecoUsuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }
        enderecosUsuarioRepository.delete(enderecoUsuarioOptional.get());
        enderecoRepository.delete(enderecoOptional.get());
    }

    public EnderecoExibicaoDTO cadastrarEnderecoAoUsuarioPorId(String cep, String complemento, int idUsuario, int numero) {
        verificaSeUsuarioExiste(idUsuario);

        if (enderecosUsuarioRepository
                .existsByEnderecoCepAndEnderecoNumeroAndUsuarioId(StringUtils.formataCep(cep), numero, idUsuario)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario já possui esse endereço cadastrado");
        }

        verificaSeUsuarioExiste(idUsuario);

        List<EnderecoUsuario> enderecos =
                enderecosUsuarioRepository.findEnderecoUsuarioByUsuario_IdOrderByIsAtivo(idUsuario);

        for (EnderecoUsuario enderecoUsuario : enderecos) {
            enderecoUsuario.setIsAtivo(StatusAtivoEnum.INATIVO);
        }

        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        Endereco endereco = cadastrarEndereco(cep, complemento, numero, idUsuario);
        enderecosUsuarioRepository.save(EnderecoUsuarioMapper.toEntity(endereco, usuario));
        return EnderecoMapper.toDTO(endereco);
    }


    public Endereco getEnderecoPeloCep(String cep) {
        ViaCepResponse response = this.viaCepClient.getCEP(cep);
        if(response.getCep()==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"CEP não encontrado");
        }
        return EnderecoMapper.of(response);
    }

    private void verificaSeUsuarioExiste(int idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }
    }
}
