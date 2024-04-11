package culinart.domain.receita;

import com.fasterxml.jackson.annotation.JsonIgnore;
import culinart.domain.avaliacao.Avaliacao;
import culinart.domain.receitaCategoria.ReceitaCategoria;
import culinart.domain.receitaPreferencia.ReceitaPreferencia;
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
@Builder
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    private Integer horas;

    private Integer minutos;

    private String descricao;

    private Integer qtdPorcoes;

    private String imagem;

    @OneToMany
    @JoinColumn(name = "receita_id")
    private List<Ingrediente> ingredientes;

    @OneToMany
    @JoinColumn(name = "receita_id")
    private List<ModoPreparo> modoPreparos;

    @OneToMany
    @JoinColumn(name = "receita_id")
    private List<Avaliacao> avaliacoes;

    @OneToMany
    @JoinColumn(name = "receita_id")
    private List<ReceitaCategoria> receitaCategorias;

    @OneToMany
    @JoinColumn(name = "receita_id")
    private List<ReceitaPreferencia> receitaPreferencias;
}
