package culinart.domain.endereco.usuario.dto;

import culinart.domain.endereco.dto.EnderecoExibicaoDTO;
import culinart.domain.usuario.dto.UsuarioExibicaoDTO;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoResponseToUsuarioDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UsuarioExibicaoDTO usuario;

    @ManyToOne
    private EnderecoExibicaoDTO endereco;

    private int isAtivo;
}
