package culinart.domain.avaliacao.dto;

import lombok.Data;

@Data
public class AvaliacaoTxtDto {
    private String nomeReceita;
    private Integer qtd_avaliacoes;
    private Double notaMedia;
}
