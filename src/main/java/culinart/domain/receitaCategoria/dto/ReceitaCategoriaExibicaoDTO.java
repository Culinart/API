package culinart.domain.receitaCategoria.dto;

import culinart.domain.categoria.Categoria;
import culinart.domain.categoria.dto.CategoriaExibicaoDTO;
import culinart.domain.preferencia.Preferencia;
import culinart.domain.preferencia.dto.PreferenciaExibicaoDTO;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaExibicaoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReceitaCategoriaExibicaoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ReceitaExibicaoDTO receitaDTO;
    private List<CategoriaExibicaoDTO> categoriaDTO;
    private List<PreferenciaExibicaoDTO> preferenciaDTO;
}
