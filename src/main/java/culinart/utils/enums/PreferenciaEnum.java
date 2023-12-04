package culinart.utils.enums;


import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum PreferenciaEnum {
    CARNES("Carnes"),
    VEGETARIANO("Vegetariano"),
    PESCETARIANO("Pescetariano"),
    VEGANO("Vegano"),
    RAPIDO("Rápido e Fácil"),
    SAUDAVEL("Fit e Saudável")
    ;

    private String nome;

}
