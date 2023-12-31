package culinart.domain.preferencia;

import culinart.utils.enums.TipoPreferenciaEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Preferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoPreferenciaEnum tipo;

    @Column(name = "cor_fundo")
    private String corFundo;

    @Column(name = "cor_texto")
    private String corTexto;
}
