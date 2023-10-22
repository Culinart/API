package culinart.domain.usuario.dto;

import culinart.domain.endereco.Endereco;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class UsuarioExibicaoDTO {

    private int id;
    private String nome;
    private String email;
    private List<Endereco> enderecos;

}
