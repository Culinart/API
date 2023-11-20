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

    @OneToMany
    private List<Ingrediente> ingredientes;

    @OneToMany
    private List<ModoPreparo> modoPreparos;

    @OneToMany
    private List<Avaliacao> avaliacoes = new ArrayList<>();
}
