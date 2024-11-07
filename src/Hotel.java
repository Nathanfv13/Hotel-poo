
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    private List<Quarto> quartos;

    public Hotel() {
        quartos = new ArrayList<>();
    }

    public void cadastrarQuarto(int numeroQuarto, Scanner scanner) {
        System.out.println("Escolha o tipo de quarto: ");
        System.out.println("1. Solteiro");
        System.out.println("2. Casal");
        System.out.println("3. Suíte");
        System.out.print("Opção: ");
        int tipoOpcao = scanner.nextInt();
        scanner.nextLine();

        String tipo;
        switch (tipoOpcao) {
            case 1 -> tipo = "Solteiro";
            case 2 -> tipo = "Casal";
            case 3 -> tipo = "Suíte";
            default -> {
                System.out.println("Opção inválida. Tipo definido como 'Indefinido'.");
                tipo = "Indefinido";
            }
        }

        System.out.print("Digite a capacidade do quarto: ");
        int capacidade = scanner.nextInt();
        System.out.print("Digite o preço da diária: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        Quarto novoQuarto = new Quarto(numeroQuarto, tipo, capacidade, preco);
        quartos.add(novoQuarto);
        System.out.println("Quarto cadastrado com sucesso.");
    }

    public void listarQuartosDisponiveis() {
        boolean temDisponivel = false;
        for (Quarto quarto : quartos) {
            if (quarto.estaDisponivel()) {
                temDisponivel = true;
                System.out.println("Quarto " + quarto.getNumeroQuarto() + " - Tipo: " + quarto.getTipo() +
                        ", Capacidade: " + quarto.getCapacidade() + ", Preço: " + quarto.getPreco() +
                        ", Status: Disponível");
            }
        }
        if (!temDisponivel) {
            System.out.println("Não há quartos disponíveis no momento.");
        }
    }

    public void alterarStatusQuarto(int numeroQuarto, String novoStatus) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumeroQuarto() == numeroQuarto) {
                quarto.setStatus(novoStatus);
                System.out.println("Status do quarto " + numeroQuarto + " atualizado para " + novoStatus);
                return;
            }
        }
        System.out.println("Quarto não encontrado.");
    }

    public Quarto getQuarto(int numeroQuarto) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumeroQuarto() == numeroQuarto) {
                return quarto;
            }
        }
        return null;
    }
}
