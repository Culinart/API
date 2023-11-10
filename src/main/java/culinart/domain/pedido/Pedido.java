package culinart.domain.pedido;

import culinart.domain.plano.Plano;
import culinart.domain.receita.Receita;
import culinart.utils.enums.StatusPedidoEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
    private Double valor;
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;
    @Column(name = "data_entrega")
    private LocalDate dataEntrega;
    @ManyToOne
    @JoinColumn(name = "plano_id") // ajuste o nome da coluna conforme necessário
    private Plano planoId;
    @ManyToMany
    @JoinTable(
            name = "pedido_receita",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "receita_id")
    )
    private List<Receita> listaReceitas;
}
