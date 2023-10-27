package culinart.domain.endereco.usuario;

import culinart.domain.endereco.Endereco;
import culinart.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Endereco endereco;

    @ManyToOne
    private Usuario usuario;

    private int isAtivo;

}
