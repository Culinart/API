package culinart.domain.usuario;

import culinart.domain.email.Assinante;
import culinart.domain.email.ReceitaEmail;
import culinart.service.email.EnviadorEmailService;
import culinart.utils.enums.PermissaoEnum;
import culinart.utils.enums.StatusAtivoEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario implements Assinante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    @Column(name = "permissao")
    private PermissaoEnum permissao;
    private String cpf;
    private String telefone;
    @Enumerated(EnumType.STRING)
    @Column(name = "is_ativo")
    private StatusAtivoEnum isAtivo;

    @Override
    public void receberReceita(EnviadorEmailService enviadorEmailService, ReceitaEmail receitaEmail, String emailEmpresa) {
        enviadorEmailService.sendEmail(
                emailEmpresa,this.email, receitaEmail.getTitulo(), receitaEmail.getConteudo());

    }
}
