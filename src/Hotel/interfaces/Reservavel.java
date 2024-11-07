package Hotel.interfaces;

public interface Reservavel {
    boolean estaDisponivel();
    void reservar() throws Hotel.exceptions.ExcecaoDeReserva;
    void cancelarReserva() throws Hotel.exceptions.ExcecaoDeReserva;
}
