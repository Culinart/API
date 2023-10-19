package culinart.domain.usuario.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnderecoExibicaoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String logradouro;
    private String cep;
    private String cidade;
    private String uf;
    private String bairro;
    private String complemento;
    private int numero;

    public EnderecoExibicaoDTO(String logradouro, String cep, String cidade, String uf, String bairro, String complemento, int numero) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
    }
}
