package culinart.domain.receita;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

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
}
