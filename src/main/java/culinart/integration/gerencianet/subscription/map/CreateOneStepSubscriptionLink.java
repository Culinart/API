package culinart.integration.gerencianet.subscription.map;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import culinart.integration.gerencianet.Credentials;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOneStepSubscriptionLink {
    public static void main(String[] args) {
    	
        /* *********  Set credentials parameters ******** */
        Credentials credentials = new Credentials();
        final LocalDate dataVencimentoBoleto = LocalDate.now().plusDays(3);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("sandbox", credentials.isSandbox());

        
        /* ************************************************* */

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", "107412");

        List<Object> items = new ArrayList<Object>();
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("name", "Assinatura Culinart");
        item1.put("amount", 1);
        item1.put("value", 1000);
        items.add(item1);


        Map<String, Object> seetings = new HashMap<String, Object>();
		seetings.put("billet_discount", 10);
		seetings.put("card_discount", 10);
		seetings.put("message", "link test");
		seetings.put("expire_at", dataVencimentoBoleto.toString());
		seetings.put("request_delivery_address", false);
		seetings.put("payment_method", "all");

        Map<String, Object> metadata = new HashMap<String, Object>();
		metadata.put("notification_url", "http://domain.com/notification");
    
        Map<String, Object> body = new HashMap<String, Object>();
        body.put("items", items);
		body.put("settings", seetings);
		body.put("metadata", metadata);
        
        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("createOneStepSubscriptionLink", params, body);
            System.out.println(response);
        }catch (EfiPayException e){
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
