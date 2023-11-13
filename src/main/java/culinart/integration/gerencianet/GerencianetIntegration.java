package culinart.integration.gerencianet;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;

import java.util.HashMap;
import java.util.Map;


public class GerencianetIntegration {

    public void configuration() {
        try {
            Map<String, Object> options = new HashMap<String, Object>();
            //Production
            options.put("client_id", "Client_Id_6a30f85ad042a4edab6443b0615c58014095e2e7");
            options.put("client_secret", "Client_Secret_b27b11fb8cb9ea31e9ad4d7298cfd73f7c81e448");

//            //HOMOL
//            options.put("client_id", "Client_Id_a2d0cc6cf4fb7067b308ddfa245a7b6ffa094e2f");
//            options.put("client_secret", "Client_Secret_2d7295adcb6f9cc445d748b65e46190c9d9e62f6");
            options.put("certificate", "./certs/homologacao-516401-Culinart-HOMOL.p12");
            options.put("sandbox", true);


            EfiPay efi = new EfiPay(options);
        } catch (
                EfiPayException e) {
            /* Gerencianet's api errors will come here */
        } catch (
                Exception ex) {
            /* Other errors will come here */
        }
    }
}
