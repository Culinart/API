package culinart.domain.receitaPreferencia;

import culinart.domain.categoria.Categoria;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.receita.Receita;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReceitaPreferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "preferencia_id")
    private Preferencia preferencia;
}
