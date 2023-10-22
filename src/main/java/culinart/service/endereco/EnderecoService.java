package culinart.service.endereco;

import culinart.domain.endereco.Endereco;
import culinart.domain.endereco.EnderecoMapper;
import culinart.domain.endereco.dto.EnderecoExibicaoDTO;
import culinart.domain.endereco.repository.EnderecoRepository;
import culinart.domain.usuario.Usuario;
import culinart.domain.usuario.repository.UsuarioRepository;
import culinart.integration.ViaCep.client.ViaCepClient;
import culinart.integration.ViaCep.dto.ViaCepResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;
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

    public EnderecoExibicaoDTO cadastrarEnderecoAoUsuarioPorId(String cep, int idUsuario, int numero) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);

        if (usuarioOptional.isEmpty()) {
            throw new IllegalArgumentException("Usuario não encontrado");
        }

        Usuario usuario = usuarioOptional.get();
        Endereco endereco = getEnderecoPeloCep(cep);

        if (verificaSeUsuarioJaTemEnderecoCadastrado(idUsuario, cep, numero)) {
            throw new IllegalArgumentException("Usuario já possui endereço cadastrado");
        } else {
            endereco.setNumero(numero);
            enderecoRepository.save(endereco);

            usuario.getEndereco().add(endereco);
            usuarioRepository.save(usuario);
        }
        return EnderecoMapper.of(endereco);
    }

    private Boolean verificaSeUsuarioJaTemEnderecoCadastrado(int id, String cep, int numero) {
        int tamanho = cep.length();

        String parte1 = cep.substring(0, tamanho - 3);
        String parte2 = cep.substring(tamanho - 3);

        String cepFormatado = parte1 + "-" + parte2;

        List<Endereco> listaEndereco = enderecoRepository.findByCepAndNumero(cepFormatado, numero);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent() && !listaEndereco.isEmpty()) {
            Usuario usuario = usuarioOptional.get();
            for (Endereco endereco : listaEndereco) {
                if (usuario.getEndereco().contains(endereco)) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public Endereco getEnderecoPeloCep(String cep) {
        ViaCepResponse response = this.viaCepClient.getCEP(cep);
        return EnderecoMapper.of(response);
    }

    public EnderecoExibicaoDTO atualizarEnderecoDoUsuarioPorId(int idEndereco, Endereco endereco) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(idEndereco);
        if (enderecoOptional.isEmpty()) {
            throw new IllegalArgumentException("Endereço não encontrado");
        }
        endereco.setId(idEndereco);
        enderecoRepository.save(endereco);
        return EnderecoMapper.of(endereco);
    }


    public void deletarEnderecoDoUsuarioPorId(int idEndereco) {
        Optional<Endereco> enderecoOptional = enderecoRepository.findById(idEndereco);
        if (enderecoOptional.isEmpty()) {
            throw new IllegalArgumentException("Endereço não encontrado");
        }
        enderecoRepository.delete(enderecoOptional.get());
    }
}
