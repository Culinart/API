package culinart.domain.receita;

import culinart.domain.avaliacao.Avaliacao;
import jakarta.persistence.*;
import culinart.domain.ingrediente.Ingrediente;
import culinart.domain.modoPreparo.ModoPreparo;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    private Integer horas;

    private Integer minutos;

    private String descricao;

    @Lob
    private byte[] imagem;

    @OneToMany
    @JoinColumn(name = "ingrediente_id")
    private List<Ingrediente> ingredientes;

    @OneToMany
    @JoinColumn(name = "modo_preparo_id")
    private List<ModoPreparo> modoPreparos;

    @OneToMany
    @JoinColumn(name = "avaliacao_id")
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}
