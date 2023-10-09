package culinart.domain.usuario.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCriacaoDTO {

    private String nome;
    private String email;
    private String senha;
    private Integer endereco;

}
