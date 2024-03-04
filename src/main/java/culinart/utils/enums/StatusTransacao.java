package culinart.utils.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusTransacao {
    NOVO("new"),
    AGUARDANDO("waiting"),
    IDENTIFICADO("identified"),
    APROVADO("approved"),
    PAGO("paid"),
    NAO_PAGO("unpaid"),
    DEVOLVIDO("refunded"),
    CONTESTADO("contested"),
    CANCELADO("canceled"),
    MARCAR_COMO_PAGO("settled"),
    LINK("link"),
    EXPIRADO("expired");

    private final String nome;
    public String obterStatus() {
        return nome;
    }
}
