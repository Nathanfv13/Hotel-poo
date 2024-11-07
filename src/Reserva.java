import java.time.LocalDate;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private boolean ativa;
    private String status;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate dataEntrada, LocalDate dataSaida) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.ativa = true;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public int getNumeroQuarto() {
        return quarto.getNumeroQuarto();
    }

    public LocalDate getDataEntrada() {
        return dataEntrada;
    }

    public LocalDate getDataSaida() {
        return dataSaida;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public String getStatus() {
        return status;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public void realizarCheckIn() {
        this.status = "Checked-in";
        this.ativa = true;
    }

    public void realizarCheckOut() {
        this.status = "Finalizada";
        this.ativa = false;
    }
}
