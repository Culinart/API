package culinart.domain.email;

import culinart.domain.receita.Receita;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReceitaEmail {

  private final UUID id = UUID.randomUUID();
  private String titulo;
  private String conteudo;
  private Receita receita;
}
