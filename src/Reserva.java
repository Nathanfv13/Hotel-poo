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
        this.status = "Aguardando check-in"; 
    }

    public void realizarCheckIn() {
        setStatus("CheckIN realizado!"); // 
    }

    public Hospede getHospede() {
        return hospede;
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

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
        if (!ativa) {
            setStatus("Finalizada"); 
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
