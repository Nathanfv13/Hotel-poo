import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaReservas {
    private List<Reserva> reservas;
    private List<Quarto> quartos;
    private List<Hospede> hospedes;
    private List<Funcionario> funcionarios;

    public SistemaReservas() {
        this.reservas = new ArrayList<>();
        this.quartos = new ArrayList<>();
        this.hospedes = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public void adicionarQuarto(Quarto quarto) {
        quarto.setStatus("Disponível");
        quartos.add(quarto);
    }

    public Quarto getQuarto(int numeroQuarto) {
        for (Quarto quarto : quartos) {
            if (quarto.getNumeroQuarto() == numeroQuarto) {
                return quarto;
            }
        }
        return null;
    }

    public void criarReserva(Hospede hospede, Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        if (quarto == null) {
            System.out.println("Erro: Quarto não encontrado.");
            return;
        }

        if (!quarto.estaDisponivel()) {
            System.out.println("Erro: Quarto " + quarto.getNumeroQuarto() + " não está disponível para reserva.");
            return;
        }

        if (verificarDisponibilidade(quarto, dataEntrada, dataSaida)) {
            Reserva novaReserva = new Reserva(hospede, quarto, dataEntrada, dataSaida);
            novaReserva.setAtiva(true);
            reservas.add(novaReserva);
            hospede.adicionarEstadia(new Estadia(quarto.getNumeroQuarto(), dataEntrada, dataSaida));
            quarto.setStatus("Aguardando check-in"); // Altera o status do quarto para "Aguardando check-in"
            System.out.println("Reserva criada com sucesso para o quarto " + quarto.getNumeroQuarto());
        } else {
            System.out.println("Erro: Quarto " + quarto.getNumeroQuarto() + " não disponível para o período selecionado.");
        }
    }

    public void cancelarReserva(int numeroQuarto) {
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroQuarto() == numeroQuarto && reserva.isAtiva()) {
                reserva.setAtiva(false);
                getQuarto(numeroQuarto).setStatus("Disponível");
                System.out.println("Reserva para o quarto " + numeroQuarto + " cancelada com sucesso.");
                return;
            }
        }
        System.out.println("Erro: Reserva não encontrada para o quarto " + numeroQuarto);
    }

    public boolean verificarDisponibilidade(Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroQuarto() == quarto.getNumeroQuarto() && reserva.isAtiva()) {
                return false;
            }
        }
        return true;
    }

    public void listarReservasAtivas() {
        System.out.println("Reservas Ativas:");
        for (Reserva reserva : reservas) {
            if (reserva.isAtiva()) {
                System.out.println("Reserva no Quarto: " + reserva.getNumeroQuarto() +
                                   ", Hóspede: " + reserva.getHospede().getNome() +
                                   ", CPF: " + reserva.getHospede().getCpf() +
                                   ", Status da Reserva: " + reserva.getStatus() +
                                   ", Entrada: " + reserva.getDataEntrada() +
                                   ", Saída: " + reserva.getDataSaida());
            }
        }
    }


    public Hospede buscarHospedePorCpf(String cpf) {
        String cpfNormalizado = normalizarCpf(cpf);
        for (Hospede hospede : hospedes) {
            if (normalizarCpf(hospede.getCpf()).equals(cpfNormalizado)) {
                return hospede;
            }
        }
        System.out.println("Hóspede não encontrado.");
        return null;
    }

    private String normalizarCpf(String cpf) {
        return cpf.replace(".", "").replace("-", "");
    }

    public Funcionario buscarFuncionarioPorCpf(String cpf) {
        String cpfNormalizado = normalizarCpf(cpf);
        for (Funcionario funcionario : funcionarios) {
            if (normalizarCpf(funcionario.getCpf()).equals(cpfNormalizado)) {
                return funcionario;
            }
        }
        System.out.println("Funcionário não encontrado.");
        return null;
    }

    public void adicionarHospede(Hospede hospede) {
        hospedes.add(hospede);
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios.add(funcionario);
    }
}
