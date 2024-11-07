import java.time.LocalDate;

public class Estadia {
    private int numeroQuarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;

    public Estadia(int numeroQuarto, LocalDate dataEntrada, LocalDate dataSaida) {
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
    }

    @Override
    public String toString() {
        return "Quarto: " + numeroQuarto + ", Entrada: " + dataEntrada + ", Saída: " + dataSaida;
    }
}
