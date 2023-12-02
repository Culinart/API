package culinart.domain.receitaCategoria;

import culinart.domain.categoria.Categoria;
import culinart.domain.receita.Receita;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ReceitaCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
