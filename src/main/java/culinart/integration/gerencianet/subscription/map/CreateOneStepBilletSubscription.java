package culinart.integration.gerencianet.subscription.map;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import culinart.domain.plano.Plano;
import culinart.domain.usuario.Usuario;
import culinart.integration.gerencianet.Credentials;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateOneStepBilletSubscription {
    public PagamentoDTO cobrarPagamento(Usuario usuario, Plano plano) {

        /* *********  Set credentials parameters ******** */
        final LocalDate dataVencimentoBoleto = LocalDate.now().plusDays(3);
        final String idPlano = "107418";

        Credentials credentials = new Credentials();
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("client_id", credentials.getClientId());
        options.put("client_secret", credentials.getClientSecret());
        options.put("sandbox", credentials.isSandbox());

        /* ************************************************* */
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("id", idPlano);

        List<Object> items = new ArrayList<Object>();
        Map<String, Object> item1 = new HashMap<String, Object>();
        item1.put("name", "Assinatura Culinart");
        item1.put("amount", 1);
        item1.put("value", plano.getValorPlano().intValue() - plano.getValorAjuste().intValue());
        items.add(item1);

        Map<String, Object> customer = new HashMap<String, Object>();
        customer.put("name", usuario.getNome().trim());
        customer.put("cpf", usuario.getCpf());
        customer.put("phone_number", usuario.getTelefone());
        customer.put("email", usuario.getEmail());

        Map<String, Object> metadata = new HashMap<String, Object>();
        metadata.put("notification_url", "http://domain.com/notification");

        Map<String, Object> discount = new HashMap<String, Object>();
        discount.put("type", "currency");
        discount.put("value", 1);

        Map<String, Object> configurations = new HashMap<String, Object>();
        configurations.put("fine", 200);
        configurations.put("interest", 33);

        Map<String, Object> bankingBillet = new HashMap<String, Object>();
        bankingBillet.put("expire_at", dataVencimentoBoleto.toString());
        bankingBillet.put("customer", customer);
        bankingBillet.put("discount", discount);
        bankingBillet.put("configurations", configurations);

        Map<String, Object> payment = new HashMap<String, Object>();
        payment.put("banking_billet", bankingBillet);

        Map<String, Object> body = new HashMap<String, Object>();
        body.put("payment", payment);
        body.put("items", items);
        body.put("metadata", metadata);

        PagamentoDTO pagamentoDTO = null;
        try {
            EfiPay efi = new EfiPay(options);
            Map<String, Object> response = efi.call("createOneStepSubscription", params, body);
            pagamentoDTO = mapearResposta(response);
            System.out.println(response);
        } catch (EfiPayException e) {
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return pagamentoDTO;
    }

    public PagamentoDTO mapearResposta(Map<String, Object> response) {
        PagamentoDTO pagamentoDTO = new PagamentoDTO();

        Map<String, Object> data = (Map<String, Object>) response.get("data");

        Map<String, Object> charge = (Map<String, Object>) data.get("charge");

        pagamentoDTO.setIdTransacao((int) charge.get("id"));
        pagamentoDTO.setStatusTransacao((String) charge.get("status"));
        pagamentoDTO.setLinkCobranca((String) data.get("link"));
        pagamentoDTO.setDataExpiracao(LocalDate.parse((String) data.get("expire_at")));


        pagamentoDTO.setIdAssinatura((int) data.get("subscription_id"));
        pagamentoDTO.setStatusAssinatura((String) data.get("status"));

        return pagamentoDTO;
    }
}

