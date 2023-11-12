package culinart.domain.email;

import culinart.service.email.EnviadorEmailService;

public interface Assinante {

    void receberReceita(EnviadorEmailService enviadorEmailService, ReceitaEmail receitaEmail, String emailEmpresa);
}
