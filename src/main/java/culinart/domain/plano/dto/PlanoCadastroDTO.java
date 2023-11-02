package culinart.domain.plano.dto;

import culinart.domain.usuario.Usuario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoCadastroDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private List<String> preferencias = new ArrayList<>();
    private Integer qtdPessoas;
    private Integer qtdRefeicoesDia;
    private BigDecimal valorPlano;
    private BigDecimal valorAjuste;
    private Integer qtdDiasSemana;
    private LocalDateTime horaEntrega;
    private String diaSemana;
    private Integer isAtivo;
    private Usuario usuario;
}
