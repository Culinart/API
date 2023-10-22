package culinart.domain.endereco;

import culinart.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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