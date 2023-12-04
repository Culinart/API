package culinart.integration.gerencianet.subscription.map;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import culinart.integration.gerencianet.Credentials;
import culinart.integration.gerencianet.subscription.dto.PlanoDataDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class CreatePlan {
    public PlanoDataDTO criarPlano() {
        /* *********  Set credential parameters ******** */
        Credentials credentials = new Credentials();
        String nomePlano = "Culinart Assinatura";
        int intervalo = 1;

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("sandbox", credentials.isSandbox());

        /* ************************************************* */

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("name", nomePlano);
        body.put("interval", intervalo);
        body.put("repeats", null);

        Map<String, Object> response = null;

        try {
            EfiPay efi = new EfiPay(options);
            response = efi.call("createPlan", new HashMap<String, String>(), body);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
            throw new ResponseStatusException(HttpStatusCode.valueOf(e.getCode()), "Error");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        if (response != null) {
            return mapearResposta(response);
        } else {
            return null;
        }
    }

    public PlanoDataDTO mapearResposta(Map<String, Object> response) {
        // Extrai o mapa contendo os dados do plano
        Map<String, Object> data = (Map<String, Object>) response.get("data");

        // Cria uma instância da classe PlanoDataDTO
        PlanoDataDTO planoDataDTO = new PlanoDataDTO();

        // Configura os atributos da classe com os valores extraídos
        planoDataDTO.setPlanId((int) data.get("plan_id"));
        planoDataDTO.setName((String) data.get("name"));
        planoDataDTO.setInterval((int) data.get("interval"));
        planoDataDTO.setRepeats((Integer) data.get("repeats"));

        // Converte a string de data para LocalDateTime
        String createdAtString = (String) data.get("created_at");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime createdAt = LocalDateTime.parse(createdAtString, formatter);
        planoDataDTO.setCreated_at(createdAt);

        return planoDataDTO;
    }
}
