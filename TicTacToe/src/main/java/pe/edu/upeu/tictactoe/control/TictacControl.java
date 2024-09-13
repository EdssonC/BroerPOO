package pe.edu.upeu.tictactoe.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TictacControl {

    @FXML
    private TextField jugador1, jugador2;

    @FXML
    private Label elTurno;

    @FXML
    private Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22, btnOn;

    @FXML
    private TableView<Partida> tablaPuntajes;

    @FXML
    private TableColumn<Partida, String> columnaPartida, columnaJugador1, columnaJugador2, columnaGanador, columnaPuntuacion, columnaEstado;

    private Button[][] tablero;
    private boolean turno = true;
    private List<Partida> partidas = new ArrayList<>();
    private int partidaActual = 1;

    @FXML
    public void initialize() {
        tablero = new Button[][]{
                {btn00, btn01, btn02},
                {btn10, btn11, btn12},
                {btn20, btn21, btn22}
        };
        deshabilitarTablero(); // Desactiva los botones al inicio
        configurarTabla();
    }

    private void configurarTabla() {
        columnaPartida.setCellValueFactory(new PropertyValueFactory<>("numero"));
        columnaJugador1.setCellValueFactory(new PropertyValueFactory<>("jugador1"));
        columnaJugador2.setCellValueFactory(new PropertyValueFactory<>("jugador2"));
        columnaGanador.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        columnaPuntuacion.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    @FXML
    void iniciarJuego(ActionEvent event) {
        btnOn.setDisable(true); // Desactiva el botón "Iniciar"
        resetGame(); // Limpia el tablero
        elTurno.setText(jugador1.getText());
        habilitarTablero(); // Habilita los botones del tablero
        partidas.add(new Partida(partidaActual++, jugador1.getText(), jugador2.getText(), "", 0, "Jugando"));
        actualizarTabla();
    }

    @FXML
    void anularJuego(ActionEvent event) {
        deshabilitarTablero();
        btnOn.setDisable(false); // Activa el botón "Iniciar"
        if (!partidas.isEmpty()) {
            Partida ultimaPartida = partidas.get(partidas.size() - 1);
            ultimaPartida.setEstado("Anulado");
            ultimaPartida.setPuntuacion(0);
            actualizarTabla();
        }
    }

    @FXML
    void accionButon(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (b.getText().isEmpty()) {
            b.setText(turno ? "X" : "O");
            if (checkWinner()) {
                String ganador = turno ? jugador1.getText() : jugador2.getText();
                elTurno.setText("Ganador: " + ganador);
                actualizarPuntaje(ganador);
                deshabilitarTablero();
            } else {
                turno = !turno;
                actualizarTurno();
            }
        }
    }

    private void actualizarTurno() {
        elTurno.setText(turno ? jugador1.getText() : jugador2.getText());
    }

    private void actualizarPuntaje(String ganador) {
        if (!partidas.isEmpty()) {
            Partida ultimaPartida = partidas.get(partidas.size() - 1);
            ultimaPartida.setGanador(ganador);
            ultimaPartida.setPuntuacion(1); // Puntaje arbitrario; cambiar según la lógica de negocio
            ultimaPartida.setEstado("Finalizado");
            actualizarTabla();
        }
    }

    private void actualizarTabla() {
        tablaPuntajes.getItems().setAll(partidas);
    }

    private void habilitarTablero() {
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                btn.setDisable(false);
                btn.setText(""); // Limpia el texto de los botones
            }
        }
    }

    private void deshabilitarTablero() {
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                btn.setDisable(true);
            }
        }
    }

    private boolean checkWinner() {
        // Lógica para determinar si hay un ganador
        // Debes implementar la verificación del ganador aquí
        return false;
    }

    public void resetGame() {
        turno = true;
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                btn.setDisable(true); // Desactiva los botones
                btn.setText(""); // Limpia el texto de los botones
            }
        }
        elTurno.setText(""); // Limpia el texto del turno
    }

    // Clase interna para representar una partida
    public static class Partida {
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

        // Getters y setters
        public int getNumero() { return numero; }
        public String getJugador1() { return jugador1; }
        public String getJugador2() { return jugador2; }
        public String getGanador() { return ganador; }
        public int getPuntuacion() { return puntuacion; }
        public String getEstado() { return estado; }

        public void setGanador(String ganador) { this.ganador = ganador; }
        public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }
        public void setEstado(String estado) { this.estado = estado; }
    }
}

