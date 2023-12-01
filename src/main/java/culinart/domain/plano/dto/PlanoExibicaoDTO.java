package culinart.domain.plano.dto;

import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.utils.enums.DiaSemanaEnum;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoExibicaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer qtdPessoas;
    private Integer qtdRefeicoesDia;
    private Integer qtdDiasSemana;
    private BigDecimal valorPlano;
    private BigDecimal valorAjuste;
    private String horaEntrega;
    private DiaSemanaEnum diaSemana;
    @Enumerated(EnumType.STRING)
    private StatusAtivoEnum isAtivo;
    private UsuarioExibicaoDTO usuario;
}
