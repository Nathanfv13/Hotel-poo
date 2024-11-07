import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SistemaReservas {
    private List<Reserva> reservas;
    private List<Quarto> quartos;
    private List<Hospede> hospedes;
    private List<Funcionario> funcionarios;

    public SistemaReservas() {
        reservas = new ArrayList<>();
        quartos = new ArrayList<>();
        hospedes = new ArrayList<>();
        funcionarios = new ArrayList<>();
    }

    
    private String normalizarCpf(String cpf) {
        return cpf.replace(".", "").replace("-", "");
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
            quarto.setStatus("Aguardando check-in"); 
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
                String statusReserva = reserva.getStatus();
                System.out.println("Reserva no Quarto: " + reserva.getNumeroQuarto() + 
                                   ", Hóspede: " + reserva.getHospede().getNome() + 
                                   ", CPF: " + reserva.getHospede().getCpf() + 
                                   ", Status da Reserva: " + statusReserva + 
                                   ", Entrada: " + reserva.getDataEntrada() + 
                                   ", Saída: " + reserva.getDataSaida());
            }
        }
    }

    public void realizarCheckIn(int numeroQuarto, LocalDate entrada, Hospede hospede) {
        Quarto quarto = getQuarto(numeroQuarto);
        
        if (quarto == null) {
            System.out.println("Check-in falhou: quarto " + numeroQuarto + " não encontrado.");
            return;
        }

        if (!quarto.getStatus().equals("Aguardando check-in")) {
            System.out.println("Check-in falhou: o quarto " + numeroQuarto + " não está Aguardando check-in.");
            return;
        }

        Reserva reservaCorrespondente = null;
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroQuarto() == numeroQuarto && reserva.isAtiva() && reserva.getHospede().equals(hospede)) {
                reservaCorrespondente = reserva;
                break;
            }
        }

        if (reservaCorrespondente == null) {
            System.out.println("Erro: Reserva não encontrada para o hóspede com CPF: " + hospede.getCpf() + " e quarto " + numeroQuarto);
            return;
        }

        reservaCorrespondente.realizarCheckIn();
        quarto.setStatus("Ocupado");
        System.out.println("Check-in realizado com sucesso no quarto " + numeroQuarto + " para o hóspede: " + hospede.getNome());
    }

    public void realizarCheckOut(int numeroQuarto, LocalDate saida) {
        for (Reserva reserva : reservas) {
            if (reserva.getNumeroQuarto() == numeroQuarto && reserva.isAtiva()) {
                reserva.setAtiva(false); 
                getQuarto(numeroQuarto).setStatus("Disponível");
                long diasEstadia = java.time.temporal.ChronoUnit.DAYS.between(reserva.getDataEntrada(), saida);
                double valorTotal = diasEstadia * getQuarto(numeroQuarto).getPreco();
                System.out.println("Check-out realizado com sucesso para o quarto " + numeroQuarto);
                System.out.println("Total de dias: " + diasEstadia + ", Valor total: R$ " + valorTotal);
                return;
            }
        }
        System.out.println("Check-out falhou: reserva não encontrada ou quarto já disponível.");
    }
}
