package culinart.domain.ingrediente;


import culinart.domain.receita.Receita;
import culinart.utils.enums.UnidadeMedidaEnum;
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
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double quantidade;
    @Enumerated(EnumType.STRING)
    private UnidadeMedidaEnum unidadeMedidaEnum;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;
}
