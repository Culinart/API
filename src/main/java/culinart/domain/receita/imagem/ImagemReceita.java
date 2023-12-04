package culinart.domain.receita.imagem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import culinart.domain.receita.Receita;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ImagemReceita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;


    @JsonIgnore // ignoramos no JSON pois não faz sentido retornar um vetor de bytes num JSON!
    @Column(length = 50 * 1024 * 1024) // 50 Mega Bytes
    @Lob
    private byte[] foto;

    @ManyToOne
    @JsonIgnore
    private Receita receita;
}
