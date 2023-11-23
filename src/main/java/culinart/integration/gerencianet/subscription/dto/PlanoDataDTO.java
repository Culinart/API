package culinart.integration.gerencianet.subscription.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlanoDataDTO {
    private int planId; //: numero_plan_id, // número da ID referente ao plano de assinatura criado
    private String name; //: "Plano de Internet - Velocidade 10 Mb", // nome do plano de assinatura
    private int interval; //: 12, // intervalo que as cobranças devem ser geradas, em meses
    private Integer repeats; // null, // número de vezes que a cobrança deve ser gerada - neste caso, indefinidamente
    private LocalDateTime created_at; //": "2016-06-28 15:48:32"
}
