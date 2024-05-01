package culinart.domain.pedido.dto;

import culinart.domain.plano.Plano;
import culinart.domain.receita.Receita;
import culinart.domain.receita.dto.ReceitaExibicaoPedidoDto;
import culinart.utils.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class PedidoByDataDto {

    private Integer id;
    private Double valor;
    private StatusPedidoEnum status;
    private LocalDate dataEntrega;
    private String logradouro;
    private Integer numero;
    private List<ReceitaExibicaoPedidoDto> listaReceitas;

}
