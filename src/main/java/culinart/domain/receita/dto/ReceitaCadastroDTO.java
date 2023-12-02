package culinart.domain.receita.dto;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.categoria.Categoria;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaCadastroDTO {

    private String nome;
    private Integer horas;
    private Integer minutos;
    private String descricao;
    private Integer qtdPorcoes;
    @Lob
    private byte[] imagem;
    private List<Ingrediente> ingredientes;
    private List<ModoPreparo> modoPreparos;
    private List<Categoria> categorias;
}
