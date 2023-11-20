package culinart.domain.receita.dto;

import culinart.domain.avaliacao.dto.AvaliacaoResponseDTO;
import culinart.domain.ingrediente.dto.IngredienteExibicaoDTO;
import culinart.domain.modoPreparo.dto.ModoPreparoExibicaoDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<IngredienteExibicaoDTO> ingredientes;
    private List<ModoPreparoExibicaoDTO> modoPreparos;
    private List<AvaliacaoResponseDTO> avaliacoes;
    private Double mediaAvaliacoes;
    private Integer qtdAvaliacoes;
}
