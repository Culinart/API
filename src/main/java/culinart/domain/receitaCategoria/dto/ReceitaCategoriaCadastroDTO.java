package culinart.domain.receitaCategoria.dto;

import culinart.domain.categoria.dto.CategoriaCadastroDTO;
import culinart.domain.preferencia.dto.PreferenciaCadastroDTO;
import culinart.domain.receita.Receita;
import lombok.Data;

import java.util.List;

@Data
public class ReceitaCategoriaCadastroDTO {
    private Integer id;
    private Receita receita;
    private List<CategoriaCadastroDTO> categoria;
    private List<PreferenciaCadastroDTO> preferencia;
}
