package culinart.domain.assinatura.pagamento.mapper;

import culinart.domain.assinatura.pagamento.dto.HistoryResponseDTO;
import culinart.domain.assinatura.pagamento.dto.PlanoResponseDTO;
import culinart.domain.assinatura.pagamento.dto.StatusSubscricaoResponseDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PagamentoMapper {
    //Preciso de um metodo que receba  um Map<String, Object> e retorne um StatusSubscricaoResponseDTO
    public StatusSubscricaoResponseDTO toStatusSubscricaoResponseDTO(Map<String, Object> map) {
        StatusSubscricaoResponseDTO statusSubscricaoResponseDTO = new StatusSubscricaoResponseDTO();

        Map<String, Object> data = (Map<String, Object>) map.get("data");
        statusSubscricaoResponseDTO.setOcurrences((Integer) data.get("occurrences"));
        statusSubscricaoResponseDTO.setNotification_url((String) data.get("notification_url"));
        statusSubscricaoResponseDTO.setNext_expire_at(LocalDate.parse((String) data.get("next_expire_at")));
        statusSubscricaoResponseDTO.setDiscount((Integer) data.get("discount"));
        statusSubscricaoResponseDTO.setCreated_at(LocalDate.parse((String) data.get("created_at")));
        statusSubscricaoResponseDTO.setSubscription_id((Integer) data.get("subscription_id"));
        statusSubscricaoResponseDTO.setNext_execute(LocalDate.parse((String) data.get("next_execution")));
        statusSubscricaoResponseDTO.setValue((Integer) data.get("value"));
        statusSubscricaoResponseDTO.setPayment_method((String) data.get("payment_method"));
        statusSubscricaoResponseDTO.setStatus((String) data.get("status"));

        // Mapeamento do objeto 'plan'
        Map<String, Object> planData = (Map<String, Object>) data.get("plan");
        PlanoResponseDTO planoResponseDTO = new PlanoResponseDTO();
        planoResponseDTO.setRepeats((Integer) planData.get("repeats"));
        planoResponseDTO.setName((String) planData.get("name"));
        planoResponseDTO.setInterval((Integer) planData.get("interval"));
        planoResponseDTO.setPlan_id((Integer) planData.get("plan_id"));
        statusSubscricaoResponseDTO.setPlan(planoResponseDTO);

        // Mapeamento da lista 'history'
        List<Map<String, Object>> historyDataList = (List<Map<String, Object>>) data.get("history");
        List<HistoryResponseDTO> historyResponseDTOList = new ArrayList<>();
        for (Map<String, Object> historyData : historyDataList) {
            HistoryResponseDTO historyResponseDTO = new HistoryResponseDTO();
            historyResponseDTO.setCharge_id((Integer) historyData.get("charge_id"));
            historyResponseDTO.setCreated_at(LocalDate.parse((String) historyData.get("created_at")));
            historyResponseDTO.setStatus((String) historyData.get("status"));
            historyResponseDTOList.add(historyResponseDTO);
        }
        statusSubscricaoResponseDTO.setHistory(historyResponseDTOList);

        return statusSubscricaoResponseDTO;
    }

}
