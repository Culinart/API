package culinart.domain.plano.dto;

import culinart.domain.usuario.Usuario;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoCadastroDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer qtdPessoas;
    private Integer qtdRefeicoesDia;
    private BigDecimal valorPlano;
    private BigDecimal valorAjuste;
    private Integer qtdDiasSemana;
    private LocalTime horaEntrega;
    private String diaSemana;
    @Enumerated(EnumType.STRING)
    private StatusAtivoEnum isAtivo;
    private Usuario usuario;
}
