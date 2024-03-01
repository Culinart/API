package culinart.integration.gerencianet.subscription.map;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import culinart.domain.assinatura.pagamento.dto.StatusPagamentoDTO;
import culinart.domain.assinatura.pagamento.dto.StatusSubscricaoResponseDTO;
import culinart.domain.assinatura.pagamento.mapper.PagamentoMapper;
import culinart.integration.gerencianet.Credentials;

import java.util.HashMap;
import java.util.Map;

public class DetailSubscription {
	public StatusPagamentoDTO atualizarStatusSubscricao(Integer idSubscricao) {
		/* *********  Set credential parameters ******** */

		Credentials credentials = new Credentials();
		StatusSubscricaoResponseDTO statusSubscricaoResponseDTO = new StatusSubscricaoResponseDTO();

		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("client_id", credentials.getClientId());
		options.put("client_secret", credentials.getClientSecret());
		options.put("sandbox", credentials.isSandbox());

		/* ************************************************* */

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("id", String.valueOf(idSubscricao));
		Map<String, Object> response = null;

		try {
			EfiPay efi = new EfiPay(options);
			response = efi.call("detailSubscription", params, new HashMap<String, Object>());
			PagamentoMapper pagamentoMapper = new PagamentoMapper();
			statusSubscricaoResponseDTO = pagamentoMapper.toStatusSubscricaoResponseDTO(response);

		}catch (EfiPayException e){
		    System.out.println(e.getCode());
		    System.out.println(e.getError());
		    System.out.println(e.getErrorDescription());
		}catch (Exception e) {
		    System.out.println(e.getMessage());
		}

		System.out.println(statusSubscricaoResponseDTO);

		if(statusSubscricaoResponseDTO.getStatus().equals("active")){
			return new StatusPagamentoDTO(statusSubscricaoResponseDTO.getStatus());
		}else if(statusSubscricaoResponseDTO.getStatus().equals("canceled")){
			return new StatusPagamentoDTO(statusSubscricaoResponseDTO.getStatus());
		}
		return new StatusPagamentoDTO("inactive");
    }
}
