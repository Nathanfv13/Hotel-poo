
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validador {
    public static boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public static boolean validarData(String data) {
        try {
            LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Data inválida. Por favor, insira a data no formato dd/MM/yyyy.");
            return false;
        }
    }

    public static boolean verificarCamposObrigatorios(String... campos) {
        for (String campo : campos) {
            if (campo == null || campo.isEmpty()) {
                System.out.println("Por favor, preencha todos os campos obrigatórios.");
                return false;
            }
        }
        return true;
    }
}