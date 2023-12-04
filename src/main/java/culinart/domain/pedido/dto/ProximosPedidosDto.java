package culinart.domain.pedido.dto;

import culinart.domain.categoria.Categoria;
import culinart.domain.receita.Receita;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ProximosPedidosDto {
    private Integer idPedido;
    private String nomeUsuario;
    private String logradouro;
    private Integer numero;
    private LocalDate dataEntrega;
    private Integer qtdReceitas;
    private Integer qtdPorcoes;
    private String receitas;
    private String categorias;
}
