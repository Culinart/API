package culinart.domain.assinatura;

import culinart.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Assinatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int assinaturaId;
    private String statusAssinatura;

    @ManyToOne
    private Usuario usuario;
}
