package culinart.domain.receita.dto;

import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.dto.CategoriaExibicaoDTO;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.ingrediente.dto.IngredienteExibicaoDTO;
import culinart.domain.modoPreparo.ModoPreparo;
import culinart.domain.modoPreparo.dto.ModoPreparoExibicaoDTO;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaPreferencia.dto.ReceitaPreferenciaExibicaoDTO;
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
    private List<IngredienteExibicaoDTO> ingredientes;
    private List<ModoPreparoExibicaoDTO> modoPreparos;
    private List<Categoria> categorias;
    private List<Preferencia> preferencias;
}
