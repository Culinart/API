package culinart.integration.ViaCep;

import culinart.integration.ViaCep.client.ViaCepClient;
import culinart.integration.ViaCep.dto.ViaCepResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViaCepIntegrationService {
    private final ViaCepClient viaCepClient;

    public ViaCepIntegrationService(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }

    public ViaCepResponse getCEP(String cep) {
        try {
            return this.viaCepClient.getCEP(cep);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cep não encontrado");
        }
    }
}
