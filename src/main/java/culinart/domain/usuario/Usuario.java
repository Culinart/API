package culinart.domain.usuario;

import culinart.domain.email.Assinante;
import culinart.domain.email.ReceitaEmail;
import culinart.service.email.EnviadorEmailService;
import culinart.utils.enums.PermissaoEnum;
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
    private PermissaoEnum permissao;
    private String telefone;

    @Column(name = "is_ativo")
    private Integer isAtivo;

    @Override
    public void receberReceita(EnviadorEmailService enviadorEmailService, ReceitaEmail receitaEmail, String emailEmpresa) {
        enviadorEmailService.sendEmail( emailEmpresa,this.email, receitaEmail.getTitulo(), receitaEmail.getConteudo());

    }
}
