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
import pe.edu.upeu.tictactoe.modelo.Partida;

import java.util.ArrayList;
import java.util.List;

@Component
public class TictacControl {

    @FXML
    Label j1,j2;

    @FXML
    private TextField jugador1, jugador2;

    @FXML
    private Label elTurno;

    @FXML
    private Button btn00, btn01, btn02, btn10, btn11, btn12, btn20, btn21, btn22, btnOn, btnAnular;

    @FXML
    private TableView<Partida> tablaPuntajes;

    @FXML
    private TableColumn<Partida, String> cPartida, cJugador1, cJugador2, cGanador, cPuntuacion, cEstado;

    private Button[][] tablero;
    private boolean turno = true; // true para jugador1 (X), false para jugador2 (O)
    private List<Partida> partidas = new ArrayList<>();
    private int partidaActual = 1;
    private int puntuacionJugador1 = 0;
    private int puntuacionJugador2 = 0;

    @FXML
    public void initialize() {
        tablero = new Button[][]{
                {btn00, btn01, btn02},
                {btn10, btn11, btn12},
                {btn20, btn21, btn22}
        };
        deshabilitarTablero();
        configurarTabla();
    }

    private void configurarTabla() {
        cPartida.setCellValueFactory(new PropertyValueFactory<>("numero"));
        cJugador1.setCellValueFactory(new PropertyValueFactory<>("jugador1"));
        cJugador2.setCellValueFactory(new PropertyValueFactory<>("jugador2"));
        cGanador.setCellValueFactory(new PropertyValueFactory<>("ganador"));
        cPuntuacion.setCellValueFactory(new PropertyValueFactory<>("puntuacion"));
        cEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }

    @FXML
    void iniciarJuego(ActionEvent event) {
        btnOn.setDisable(true); // desactiva el botón Iniciar
        btnAnular.setDisable(false); // activa el botón Anular
        resetear();
        elTurno.setText(jugador1.getText());
        habilitarTablero();
        partidas.add(new Partida(partidaActual++, jugador1.getText(), jugador2.getText(), "", 0, "Jugando"));
        actualizarTabla();
    }

    @FXML
    void anularJuego(ActionEvent event) {
        deshabilitarTablero();
        btnOn.setDisable(false); // activa el botón Iniciar de nuevo
        btnAnular.setDisable(true); // desactiva el botón Anular despues de que sea haya anulado el juego

        if (!partidas.isEmpty()) {
            Partida ultimaPartida = partidas.get(partidas.size() - 1);
            ultimaPartida.setEstado("Anulado");
            ultimaPartida.setPuntuacion(0); //si se anula el juego la puntuación es 0
            actualizarTabla();
        }
    }

    @FXML
    void accionButon(ActionEvent e) {
        Button b = (Button) e.getSource();
        if (b.getText().isEmpty()) {
            b.setText(turno ? "X" : "O");
            if (ganador()) {
                String ganador = turno ? jugador1.getText() : jugador2.getText();
                elTurno.setText("Ganador: " + ganador);
                actualizarPuntaje(ganador);
                deshabilitarTablero();
                btnOn.setDisable(false); // vuelve a activar el botón Iniciar para jugar nuevamente
                btnAnular.setDisable(true); // desactiva el botón Anular después de terminar la partida
            } else if (empate()) {
                elTurno.setText("Empate");
                deshabilitarTablero();
                actualizarEmpate();
                btnOn.setDisable(false);
                btnAnular.setDisable(true);
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
            ultimaPartida.setPuntuacion(1);
            ultimaPartida.setEstado("Finalizado");

            // puntuaciones de jugadores
            if (ganador.equals(jugador1.getText())) {
                puntuacionJugador1++;
            } else {
                puntuacionJugador2++;
            }
            actualizarPuntuaciones(); // para actualizar el numero de victorias
            actualizarTabla();
        }
    }

    // muestra numero de victorias
    private void actualizarPuntuaciones() {
        j1.setText(puntuacionJugador1 + " victorias");
        j2.setText(puntuacionJugador2 + " victorias");
    }
    //para no agregar puntaje despues del empate
    private void actualizarEmpate() {
        if (!partidas.isEmpty()) {
            Partida ultimaPartida = partidas.get(partidas.size() - 1);
            ultimaPartida.setEstado("Empate");
            ultimaPartida.setPuntuacion(0); // no se agregan puntos
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
                btn.setText("");
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

    // Método para comprobar si hay un ganador
    private boolean ganador() {
        for (int i = 0; i < 3; i++) {
            // comprueba filas
            if (!tablero[i][0].getText().isEmpty() &&
                    tablero[i][0].getText().equals(tablero[i][1].getText()) &&
                    tablero[i][0].getText().equals(tablero[i][2].getText())) {
                return true;
            }
        }
        // comprueba columnas
        for (int i = 0; i < 3; i++) {
            if (!tablero[0][i].getText().isEmpty() &&
                    tablero[0][i].getText().equals(tablero[1][i].getText()) &&
                    tablero[0][i].getText().equals(tablero[2][i].getText())) {
                return true;
            }
        }
        // comprueba las diagonales
        if (!tablero[0][0].getText().isEmpty() &&
                tablero[0][0].getText().equals(tablero[1][1].getText()) &&
                tablero[0][0].getText().equals(tablero[2][2].getText())) {
            return true;
        }
        if (!tablero[0][2].getText().isEmpty() &&
                tablero[0][2].getText().equals(tablero[1][1].getText()) &&
                tablero[0][2].getText().equals(tablero[2][0].getText())) {
            return true;
        }
        return false;
    }

    // Método para comprobar si hay empate
    private boolean empate() {
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                if (btn.getText().isEmpty()) {
                    return false; // si hay espacios vacios no hay empate
                }
            }
        }
        return true; // Si no hay espacios vacios si hay empate
    }
    //hace que el tablero de michi vuelva a estar vacio y el turno tambien
    public void resetear() {
        turno = true;
        for (Button[] fila : tablero) {
            for (Button btn : fila) {
                btn.setDisable(true);
                btn.setText("");
            }
        }
        elTurno.setText("");
    }
}
