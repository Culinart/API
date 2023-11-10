package culinart.domain.usuarioPreferencia;

import culinart.domain.preferencia.Preferencia;
import culinart.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioPreferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @OneToMany
    private List<Preferencia> preferencias;
}
