package culinart.domain.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    @NotBlank(message = "Nome Inválido")
    @Size(min = 3, message = "Minimo 3 caracteres")
    private String nome;

    @NotBlank(message = "Email Inválido")
    @Email(message = "Email Inválido")
    private String email;

    @NotBlank(message = "Senha Inválida")
    @Size(min = 8, message = ("Minimo 3 caracteres"))
    private String senha;
}
