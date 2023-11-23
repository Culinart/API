package culinart.domain.pagamento;

import culinart.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int idAssinatura;
    private String statusAssinatura;
    private int idTransacao;
    private String statusTransacao;
    private String linkCobranca;
    private LocalDate dataExpiracao;

    @ManyToOne
    private Usuario usuario;
}
