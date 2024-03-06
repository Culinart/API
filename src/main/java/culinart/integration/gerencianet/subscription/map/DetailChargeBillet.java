package culinart.integration.gerencianet.subscription.map;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import culinart.domain.assinatura.pagamento.dto.ChargeResponsePaymentDTO;
import culinart.integration.gerencianet.Credentials;

import java.util.HashMap;
import java.util.Map;

public class DetailChargeBillet {
    public ChargeResponsePaymentDTO exibirChargeDetail(Integer idSubscricao) {
        /* *********  Set credential parameters ******** */

        Credentials credentials = new Credentials();

        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("sandbox", credentials.isSandbox());

        /* ************************************************* */

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(idSubscricao));
        ChargeResponsePaymentDTO chargeResponse = new ChargeResponsePaymentDTO();

        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("detailCharge", params, new HashMap<String, Object>());
            chargeResponse = mapearChargeResponsePayment(response);
        }catch (EfiPayException e){
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chargeResponse;
    }

    public ChargeResponsePaymentDTO mapearChargeResponsePayment(Map<String, Object> response) {
        Map<String, Object> data = (Map<String, Object>) response.get("data");
        Map<String, Object> payment = (Map<String, Object>) data.get("payment");
        Map<String, Object> banking_billet = (Map<String, Object>) payment.get("banking_billet");

        return ChargeResponsePaymentDTO.builder()
                .dataExpiracao(banking_billet.get("expire_at").toString())
                .linkPagamento(banking_billet.get("link").toString())
                .build();
    }
}
