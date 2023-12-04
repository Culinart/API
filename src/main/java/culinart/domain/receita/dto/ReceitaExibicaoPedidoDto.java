package culinart.domain.receita.dto;

import culinart.domain.categoria.dto.CategoriaCardDto;
import culinart.domain.preferencia.dto.PreferenciaCardDto;
import lombok.Data;

import java.util.List;
@Data
public class ReceitaExibicaoPedidoDto {
    private Integer id;
    private String nome;
    private Integer horas;
    private Integer minutos;
    private Integer qtd_porcoes;
    private List<PreferenciaCardDto> preferencias;
    private List<CategoriaCardDto> categorias;
}
