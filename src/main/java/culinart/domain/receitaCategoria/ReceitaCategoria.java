package culinart.domain.receitaCategoria;

import culinart.domain.categoria.Categoria;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.receita.Receita;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity(name = "receita_categoria")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReceitaCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Receita receita;

    @OneToMany
    private List<Categoria> categoria;

    @OneToMany
    private List<Preferencia> preferencia;
}
