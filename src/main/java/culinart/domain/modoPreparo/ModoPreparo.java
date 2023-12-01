package culinart.domain.modoPreparo;

import culinart.domain.receita.Receita;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ModoPreparo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String passo;

    @ManyToOne
    private Receita receita;
}
