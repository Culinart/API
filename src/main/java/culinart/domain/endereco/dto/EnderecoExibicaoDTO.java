package culinart.domain.endereco.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoExibicaoDTO {
    private Integer id;
    private String logradouro;
    private String cep;
    private String cidade;
    private String uf;
    private String bairro;
    private String complemento;
    private int numero;

    public EnderecoExibicaoDTO(Integer id, String logradouro, String cep, String cidade, String uf, String bairro, String complemento, int numero) {
        this.id = id;
        this.logradouro = logradouro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
    }
}
