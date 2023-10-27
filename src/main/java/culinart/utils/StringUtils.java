package culinart.utils;

public class StringUtils {
    public static String formataCep(String cep){
        int tamanho = cep.length();

        String parte1 = cep.substring(0, tamanho - 3);
        String parte2 = cep.substring(tamanho - 3);

        return parte1 + "-" + parte2;
    }
}
