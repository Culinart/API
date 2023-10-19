package culinart.domain.usuario;

import jakarta.persistence.*;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Endereco {

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

    public Endereco(String logradouro, String cep, String cidade, String uf, String bairro, String complemento, int numero) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.cidade = cidade;
        this.uf = uf;
        this.bairro = bairro;
        this.complemento = complemento;
        this.numero = numero;
    }

    public Endereco() {
    }
}
