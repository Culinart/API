package culinart.domain.plano.dto;

import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import culinart.utils.enums.DiaSemanaEnum;
import culinart.utils.enums.PreferenciaEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoExibicaoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private List<PreferenciaEnum> preferences = new ArrayList<>();
    private Integer qtdPessoas;
    private Integer qtdRefeicoesDia;
    private Integer qtdDiasSemana;
    private LocalDateTime horaEntrega;
    private DiaSemanaEnum diaSemana;
    private Integer isAtivo;
    private UsuarioExibicaoDTO usuario;
}
