package culinart.utils;

import java.time.DayOfWeek;

public class DiaSemanaIngles {
    public DayOfWeek obterDayOfWeek(String diaSemana) {
        switch (diaSemana.toLowerCase()) {
            case "segunda":
                return DayOfWeek.MONDAY;
            case "terça":
                return DayOfWeek.TUESDAY;
            case "quarta":
                return DayOfWeek.WEDNESDAY;
            case "quinta":
                return DayOfWeek.THURSDAY;
            case "sexta":
                return DayOfWeek.FRIDAY;
            case "sábado":
                return DayOfWeek.SATURDAY;
            case "domingo":
                return DayOfWeek.SUNDAY;
            default:
                return null;
        }
    }
}
