package culinart.domain.plano;

import culinart.domain.usuario.Usuario;
import culinart.utils.enums.DiaSemanaEnum;
import culinart.utils.enums.PreferenciaEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<PreferenciaEnum> preferences = new ArrayList<>();

    private Integer qtdPessoas;

    private Integer qtdRefeicoesDia;

    private BigDecimal valorPlano;

    private BigDecimal valorAjuste;

    private Integer qtdDiasSemana;

    private String horaEntrega;

    private DiaSemanaEnum diaSemana;

    private Integer isAtivo;

    @OneToOne
    private Usuario usuario;
}
