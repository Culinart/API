package culinart.domain.endereco.dto.mapper;

import culinart.domain.endereco.Endereco;
import culinart.domain.endereco.dto.EnderecoExibicaoDTO;
import culinart.integration.ViaCep.dto.ViaCepResponse;

import java.util.ArrayList;
import java.util.List;

public class EnderecoMapper {
    public static EnderecoExibicaoDTO toDTO(Endereco endereco){
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
    public static List<EnderecoExibicaoDTO> toEnderecoDTO(List<Endereco> enderecos){
        if(enderecos.isEmpty()){
            return new ArrayList<>();
        }

        List<EnderecoExibicaoDTO> enderecoExibicaoDTOS = new ArrayList<>();

        for (Endereco enderecoDaVez : enderecos){
            enderecoExibicaoDTOS.add(new EnderecoExibicaoDTO(
                    enderecoDaVez.getId(),
                    enderecoDaVez.getLogradouro(),
                    enderecoDaVez.getCep(),
                    enderecoDaVez.getCidade(),
                    enderecoDaVez.getUf(),
                    enderecoDaVez.getBairro(),
                    enderecoDaVez.getComplemento(),
                    enderecoDaVez.getNumero()
            ));
        }
        return enderecoExibicaoDTOS;
    }

    public static Endereco of(ViaCepResponse response) {
        return new Endereco(
               null,
                response.getLogradouro(),
                response.getCep(),
                response.getLocalidade(),
                response.getUf(),
                response.getBairro(),
                null,
                0
        );
    }
}
