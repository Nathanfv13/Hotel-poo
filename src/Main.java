import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaReservas sistemaReservas = new SistemaReservas();
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Sistema de Gerenciamento de Hotel ---");
            System.out.println("1. Gerenciar Quartos");
            System.out.println("2. Gerenciar Funcionários");
            System.out.println("3. Gerenciar Hóspedes");
            System.out.println("4. Gerenciar Reservas");
            System.out.println("5. Realizar Check-in/Check-out");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> hotelOptions(hotel, scanner);
                case 2 -> funcionarioOptions(sistemaReservas, scanner);
                case 3 -> hospedeOptions(sistemaReservas, scanner);
                case 4 -> reservaOptions(sistemaReservas, hotel, scanner);
                case 5 -> checkinOptions(hotel, sistemaReservas, scanner);
                case 0 -> running = false;
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
        System.out.println("Sistema encerrado.");
    }

    static void hotelOptions(Hotel hotel, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Gerenciamento de Quartos ---");
            System.out.println("1. Cadastrar novo quarto");
            System.out.println("2. Listar quartos disponíveis");
            System.out.println("3. Alterar status do quarto");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();
                    scanner.nextLine();
                    hotel.cadastrarQuarto(numeroQuarto, scanner);
                }
                case 2 -> hotel.listarQuartosDisponiveis();
                case 3 -> {
                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo status (Disponível, Ocupado, Manutenção, Aguardando check-in): ");
                    String status = scanner.nextLine();
                    hotel.alterarStatusQuarto(numeroQuarto, status);
                }
                case 0 -> back = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    static void funcionarioOptions(SistemaReservas sistemaReservas, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Gerenciamento de Funcionários ---");
            System.out.println("1. Cadastrar novo funcionário");
            System.out.println("2. Editar funcionário");
            System.out.println("3. Registrar horas trabalhadas e calcular salário");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Digite o nome do funcionário: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do funcionário: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite o cargo do funcionário: ");
                    String cargo = scanner.nextLine();
                    System.out.print("Digite o salário por hora: ");
                    double salarioPorHora = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Digite o turno do funcionário (ex: manhã/tarde/noite): ");
                    String turno = scanner.nextLine();

                    Funcionario novoFuncionario = new Funcionario(nome, cpf, cargo, salarioPorHora, turno);
                    sistemaReservas.adicionarFuncionario(novoFuncionario);
                    System.out.println("Funcionário cadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Digite o CPF do funcionário para editar: ");
                    String cpf = scanner.nextLine();
                    Funcionario funcionario = sistemaReservas.buscarFuncionarioPorCpf(cpf);

                    if (funcionario != null) {
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Digite o novo cargo: ");
                        String novoCargo = scanner.nextLine();
                        System.out.print("Digite o novo turno: ");
                        String novoTurno = scanner.nextLine();

                        funcionario.setNome(novoNome);
                        funcionario.setCargo(novoCargo);
                        funcionario.setTurno(novoTurno);
                        System.out.println("Informações do funcionário atualizadas com sucesso.");
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o CPF do funcionário para registrar horas: ");
                    String cpf = scanner.nextLine();
                    Funcionario funcionario = sistemaReservas.buscarFuncionarioPorCpf(cpf);

                    if (funcionario != null) {
                        System.out.print("Digite o número de horas trabalhadas: ");
                        double horas = scanner.nextDouble();
                        scanner.nextLine();
                        funcionario.registrarHoras(horas);
                        System.out.println("Horas registradas com sucesso.");
                        System.out.println("Salário total: R$ " + funcionario.calcularSalario());
                    } else {
                        System.out.println("Funcionário não encontrado.");
                    }
                }
                case 0 -> back = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    static void hospedeOptions(SistemaReservas sistemaReservas, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Gerenciamento de Hóspedes ---");
            System.out.println("1. Cadastrar novo hóspede");
            System.out.println("2. Editar hóspede");
            System.out.println("3. Visualizar histórico de estadias");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Digite o nome do hóspede: ");
                    String nome = scanner.nextLine();
                    System.out.print("Digite o CPF do hóspede (formato: XXX.XXX.XXX-XX): ");
                    String cpf = scanner.nextLine();
                    System.out.print("Digite a data de nascimento (dd/MM/yyyy): ");
                    String dataNascimentoStr = scanner.nextLine();
                    System.out.print("Digite o endereço do hóspede: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Digite o número de contato do hóspede: ");
                    String contato = scanner.nextLine();

                    LocalDate dataNascimento;
                    try {
                        dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Data de nascimento inválida. Por favor, use o formato dd/MM/yyyy.");
                        return;
                    }

                    Hospede novoHospede = new Hospede(nome, cpf, dataNascimento, endereco, contato);
                    sistemaReservas.adicionarHospede(novoHospede);
                    System.out.println("Hóspede cadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Digite o CPF do hóspede para editar: ");
                    String cpf = scanner.nextLine();
                    Hospede hospede = sistemaReservas.buscarHospedePorCpf(cpf);

                    if (hospede != null) {
                        System.out.print("Digite o novo nome: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Digite o novo endereço: ");
                        String novoEndereco = scanner.nextLine();
                        System.out.print("Digite o novo contato: ");
                        String novoContato = scanner.nextLine();

                        hospede.editarInformacoes(novoNome, novoEndereco, novoContato);
                        System.out.println("Informações do hóspede atualizadas com sucesso.");
                    } else {
                        System.out.println("Hóspede não encontrado.");
                    }
                }
                case 3 -> {
                    System.out.print("Digite o CPF do hóspede para visualizar o histórico: ");
                    String cpf = scanner.nextLine();
                    Hospede hospede = sistemaReservas.buscarHospedePorCpf(cpf);

                    if (hospede != null) {
                        hospede.listarHistoricoEstadias();
                    } else {
                        System.out.println("Hóspede não encontrado.");
                    }
                }
                case 0 -> back = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    static void reservaOptions(SistemaReservas sistemaReservas, Hotel hotel, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Gerenciamento de Reservas ---");
            System.out.println("1. Criar reserva");
            System.out.println("2. Cancelar reserva");
            System.out.println("3. Listar reservas ativas");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Digite o CPF do hóspede: ");
                    String cpf = scanner.nextLine();
                    Hospede hospede = sistemaReservas.buscarHospedePorCpf(cpf);
                    if (hospede == null) {
                        System.out.println("Hóspede não encontrado. Cadastre o hóspede antes de fazer a reserva.");
                        return;
                    }

                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();
                    scanner.nextLine();
                    Quarto quarto = hotel.getQuarto(numeroQuarto);
                    if (quarto == null || !quarto.estaDisponivel()) {
                        System.out.println("Quarto não disponível.");
                        return;
                    }

                    System.out.print("Digite a data de entrada (dd/MM/yyyy): ");
                    String dataEntradaStr = scanner.nextLine();
                    System.out.print("Digite a data de saída (dd/MM/yyyy): ");
                    String dataSaidaStr = scanner.nextLine();

                    LocalDate dataEntrada;
                    LocalDate dataSaida;
                    try {
                        dataEntrada = LocalDate.parse(dataEntradaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        dataSaida = LocalDate.parse(dataSaidaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        if (!dataSaida.isAfter(dataEntrada)) {
                            System.out.println("Data de saída deve ser posterior à data de entrada.");
                            return;
                        }
                    } catch (DateTimeParseException e) {
                        System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
                        return;
                    }

                    if (sistemaReservas.verificarDisponibilidade(quarto, dataEntrada, dataSaida)) {
                        sistemaReservas.criarReserva(hospede, quarto, dataEntrada, dataSaida);
                        System.out.println("Reserva criada com sucesso!");
                    } else {
                        System.out.println("Quarto não disponível para o período selecionado.");
                    }
                }
                case 2 -> {
                    System.out.print("Digite o número do quarto da reserva a ser cancelada: ");
                    int numeroQuarto = scanner.nextInt();
                    scanner.nextLine();
                    sistemaReservas.cancelarReserva(numeroQuarto);
                }
                case 3 -> sistemaReservas.listarReservasAtivas();
                case 0 -> back = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    static void checkinOptions(Hotel hotel, SistemaReservas sistemaReservas, Scanner scanner) {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Check-in/Check-out ---");
            System.out.println("1. Realizar Check-in");
            System.out.println("2. Realizar Check-out");
            System.out.println("0. Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite o CPF do hóspede: ");
                    String cpf = scanner.nextLine();
                    Hospede hospede = sistemaReservas.buscarHospedePorCpf(cpf);
                    if (hospede == null) {
                        System.out.println("Hóspede não encontrado.");
                        return;
                    }

                    Quarto quarto = hotel.getQuarto(numeroQuarto);
                    if (quarto == null) {
                        System.out.println("Quarto não encontrado.");
                        return;
                    }

                    if (!quarto.getStatus().equalsIgnoreCase("Aguardando check-in")) {
                        System.out.println("Quarto não está disponível para check-in.");
                        return;
                    }

                   
                    quarto.setStatus("Ocupado"); 
                    
                   
                    for (Reserva reserva : sistemaReservas.getReservas()) {
                        if (reserva.getNumeroQuarto() == numeroQuarto && reserva.isAtiva()) {
                            reserva.realizarCheckIn(); 
                            System.out.println("Check-in realizado com sucesso no quarto " + numeroQuarto + " para o hóspede: " + hospede.getNome());
                            break;
                        }
                    }
                }
                case 2 -> {
                    System.out.print("Digite o número do quarto: ");
                    int numeroQuarto = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Digite a data de saída (dd/MM/yyyy): ");
                    String dataSaidaStr = scanner.nextLine();
                    LocalDate dataSaida;
                    try {
                        dataSaida = LocalDate.parse(dataSaidaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                    } catch (DateTimeParseException e) {
                        System.out.println("Data inválida. Por favor, use o formato dd/MM/yyyy.");
                        return;
                    }

                    Quarto quarto = hotel.getQuarto(numeroQuarto);
                    if (quarto == null) {
                        System.out.println("Erro: Quarto número " + numeroQuarto + " não encontrado.");
                        return;
                    }

                    if (!quarto.getStatus().equalsIgnoreCase("Ocupado")) {
                        System.out.println("Check-out falhou: O quarto não está ocupado.");
                        return;
                    }

                    
                    boolean reservaEncontrada = false;
                    for (Reserva reserva : sistemaReservas.getReservas()) {
                        if (reserva.getNumeroQuarto() == numeroQuarto && reserva.isAtiva()) {
                            reserva.setAtiva(false);
                            reservaEncontrada = true;

                            
                            long diasEstadia = java.time.temporal.ChronoUnit.DAYS.between(reserva.getDataEntrada(), dataSaida);
                            if (diasEstadia < 0) {
                                System.out.println("Erro: A data de saída é anterior à data de entrada.");
                                return;
                            }

                            double valorTotal = diasEstadia * quarto.getPreco();
                            System.out.println("Check-out realizado com sucesso para o quarto " + numeroQuarto);
                            System.out.println("Total de dias: " + diasEstadia + ", Valor total: R$ " + valorTotal);
                            break;
                        }
                    }

                    if (!reservaEncontrada) {
                        System.out.println("Erro: Reserva ativa não encontrada para o quarto " + numeroQuarto);
                    } else {
                        quarto.setStatus("Disponível"); 
                    }
                }
                case 0 -> back = true;
                default -> System.out.println("Opção inválida.");
            }
        }
    }



}
