package pe.edu.upeu.tictactoe.modelo;

public class Partida {

    private int numero;
    private String jugador1;
    private String jugador2;
    private String ganador;
    private int puntuacion;
    private String estado;

    public Partida(int numero, String jugador1, String jugador2, String ganador, int puntuacion, String estado) {
        this.numero = numero;
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.ganador = ganador;
        this.puntuacion = puntuacion;
        this.estado = estado;
    }

    public int getNumero() {
        return numero;
    }

    public String getJugador1() {
        return jugador1;
    }

    public String getJugador2() {
        return jugador2;
    }

    public String getGanador() {
        return ganador;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
