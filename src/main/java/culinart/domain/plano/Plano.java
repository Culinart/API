package culinart.domain.plano;

import culinart.domain.usuario.Usuario;
import culinart.utils.enums.DiaSemanaEnum;
import culinart.utils.enums.PreferenciaEnum;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class Plano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer qtdPessoas;

    private Integer qtdRefeicoesDia;

    private BigDecimal valorPlano;

    private BigDecimal valorAjuste;

    private Integer qtdDiasSemana;

    private LocalTime horaEntrega;

    @Enumerated(EnumType.STRING)
    private DiaSemanaEnum diaSemana;

    @Enumerated(EnumType.STRING)
    private StatusAtivoEnum isAtivo;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}

