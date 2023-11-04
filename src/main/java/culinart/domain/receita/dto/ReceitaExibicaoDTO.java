package culinart.domain.receita.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReceitaExibicaoDTO {
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private Double tempoPreparo;
    private String descricao;
}
