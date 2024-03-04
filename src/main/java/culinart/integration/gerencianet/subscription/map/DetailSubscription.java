package culinart.integration.gerencianet.subscription.map;

import br.com.efi.efisdk.EfiPay;
import br.com.efi.efisdk.exceptions.EfiPayException;
import culinart.domain.assinatura.pagamento.dto.HistoryResponseDTO;
import culinart.domain.assinatura.pagamento.dto.StatusPagamentoDTO;
import culinart.integration.gerencianet.Credentials;
import culinart.integration.gerencianet.subscription.dto.PagamentoDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailSubscription {
    public List<HistoryResponseDTO> exibirListaDePagamentos(Integer idSubscricao) {
        /* *********  Set credential parameters ******** */

        Credentials credentials = new Credentials();
		List<HistoryResponseDTO> historyResponseDTOList = new ArrayList<>();

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
			historyResponseDTOList = mapearResposta(response);
		} catch (EfiPayException e) {
            System.out.println(e.getCode());
            System.out.println(e.getError());
            System.out.println(e.getErrorDescription());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return historyResponseDTOList;
    }

	public List<HistoryResponseDTO> mapearResposta(Map<String, Object> response) {
		Map<String, Object> data = (Map<String, Object>) response.get("data");
		List<Map<String, Object>> historyList = (List<Map<String, Object>>) data.get("history");

		List<HistoryResponseDTO> historyResponseList = new ArrayList<>();

		for (Map<String, Object> history : historyList) {
			HistoryResponseDTO historyResponseDTO = new HistoryResponseDTO();
			historyResponseDTO.setCharge_id((Integer) history.get("charge_id"));
			historyResponseDTO.setCreated_at((String) history.get("created_at"));
			historyResponseDTO.setStatus((String) history.get("status"));

			historyResponseList.add(historyResponseDTO);
		}

		return historyResponseList;
	}
}
