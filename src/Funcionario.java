
public class Funcionario {
    private String nome;
    private String cpf;
    private String cargo;
    private double salarioPorHora;
    private String turno;
    private double horasTrabalhadas;

    public Funcionario(String nome, String cpf, String cargo, double salarioPorHora, String turno) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salarioPorHora = salarioPorHora;
        this.turno = turno;
        this.horasTrabalhadas = 0.0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void registrarHoras(double horas) {
        this.horasTrabalhadas += horas;
    }

    public double calcularSalario() {
        return horasTrabalhadas * salarioPorHora;
    }

    @Override
    public String toString() {
        return "Funcionário: " + nome + " | Cargo: " + cargo + " | Salário por Hora: R$ " + salarioPorHora + 
               " | Turno: " + turno + " | Horas Trabalhadas: " + horasTrabalhadas + 
               " | Salário Total: R$ " + calcularSalario();
    }
}