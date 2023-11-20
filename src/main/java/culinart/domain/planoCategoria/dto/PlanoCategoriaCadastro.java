package culinart.domain.planoCategoria.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlanoCategoriaCadastro {
    private int planoId;
    private List<CategoriaId> categoriaId;
}
