package culinart.domain.pedido;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    private Double valor;
    private String status;
    @Column(name = "data_entrega")
    private LocalDate dataEntrega;

    private List<Receita> listaReceitas;
}
