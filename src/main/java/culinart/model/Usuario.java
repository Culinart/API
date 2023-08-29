package culinart.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private Long id;
    private String nome;
    private String login;
    private String senha;
    private Endereco endereco;
    private Boolean Administrador;
}
