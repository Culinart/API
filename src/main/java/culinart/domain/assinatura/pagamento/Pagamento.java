package culinart.domain.assinatura.pagamento;

import culinart.domain.assinatura.Assinatura;
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
    private int transacaoId;
    private String statusTransacao;
    private String linkCobranca;
    private LocalDate dataExpiracao;

    @ManyToOne
    private Assinatura assinatura;
}
