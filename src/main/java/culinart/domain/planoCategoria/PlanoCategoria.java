package culinart.domain.planoCategoria;

import culinart.domain.categoria.Categoria;
import culinart.domain.plano.Plano;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanoCategoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Plano plano;

    @ManyToOne
    private Categoria categoria;
}
