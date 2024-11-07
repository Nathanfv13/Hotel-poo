import Hotel.interfaces.Reservavel;
import Hotel.exceptions.ExcecaoDeReserva;

public class Quarto implements Reservavel {
    private int numeroQuarto;
    private String tipo;
    private int capacidade;
    private double preco;
    private String status; 

    public Quarto(int numeroQuarto, String tipo, int capacidade, double preco) {
        this.numeroQuarto = numeroQuarto;
        this.tipo = tipo;
        this.capacidade = capacidade;
        this.preco = preco;
        this.status = "Disponível"; 
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public String getTipo() {
        return tipo;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public double getPreco() {
        return preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean estaDisponivel() {
        return status.equals("Disponível");
    }

    public boolean aguardandoCheckIn() {
        return status.equals("Aguardando check-in");
    }

    @Override
    public void reservar() throws ExcecaoDeReserva {
        if (!estaDisponivel()) {
            throw new ExcecaoDeReserva("Não é possível reservar: o quarto está " + status);
        }
        this.status = "Aguardando check-in"; 
    }

    @Override
    public void cancelarReserva() throws ExcecaoDeReserva {
        if (estaDisponivel()) {
            throw new ExcecaoDeReserva("O quarto não está reservado.");
        }
        this.status = "Disponível"; 
    }

    @Override
    public String toString() {
        return "Quarto Número: " + numeroQuarto + ", Tipo: " + tipo + ", Capacidade: " + capacidade +
               ", Preço: R$ " + preco + ", Status: " + status;
    }
}
