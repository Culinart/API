package culinart.domain.endereco;

import culinart.domain.endereco.dto.EnderecoExibicaoDTO;
import culinart.integration.ViaCep.dto.ViaCepResponse;


public class EnderecoMapper {
    public static EnderecoExibicaoDTO of(Endereco endereco) {
        return new EnderecoExibicaoDTO(
                endereco.getId(),
                endereco.getLogradouro(),
                endereco.getCep(),
                endereco.getCidade(),
                endereco.getUf(),
                endereco.getBairro(),
                endereco.getComplemento(),
                endereco.getNumero()
        );
    }

    public static Endereco of(ViaCepResponse response) {
        return new Endereco(
                response.getLogradouro(),
                response.getCep(),
                response.getLocalidade(),
                response.getUf(),
                response.getBairro(),
                response.getComplemento(),
                0
        );
    }

}

