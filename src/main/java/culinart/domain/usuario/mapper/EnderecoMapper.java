package culinart.domain.usuario.mapper;

import culinart.domain.usuario.Endereco;
import culinart.domain.usuario.dto.EnderecoExibicaoDTO;
import culinart.integration.ViaCep.dto.ViaCepResponse;


public class EnderecoMapper {
    public static EnderecoExibicaoDTO of(Endereco endereco) {
        return new EnderecoExibicaoDTO(
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getBairro(),
                endereco.getComplemento(),
                0
        );
    }

    public static Endereco of(ViaCepResponse response) {
        return new Endereco(response.getLogradouro(),
                response.getCep(),
                response.getLocalidade(),
                response.getUf(),
                response.getBairro(),
                response.getComplemento(),
                0
        );
    }

}
