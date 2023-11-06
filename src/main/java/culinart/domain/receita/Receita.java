package culinart.domain.receita;

import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.modoPreparo.ModoPreparo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receita {

    @Id
    @GeneratedValue
    private Integer id;
    private String nome;

    @Column(name = "tempo_preparo")
    private Double tempoPreparo;

    private String descricao;

    @OneToMany
    private List<Ingrediente> ingredientes;

    @OneToMany
    private List<ModoPreparo> modoPreparos;
}
