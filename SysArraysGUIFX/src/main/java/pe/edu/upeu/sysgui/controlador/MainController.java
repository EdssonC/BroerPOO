package pe.edu.upeu.sysgui.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysgui.modelo.Persona;
import pe.edu.upeu.sysgui.service.RegistroAsistencia;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Component
public class MainController {
    @Autowired
    private ApplicationContext context;
    @FXML
    private TextField nombreField;
    @FXML
    private TextField asisRegField;
    @FXML
    private Button btnReg;
    @FXML
    private ListView<Persona> listView;
    private ObservableList<Persona> observableList;
    @Autowired
    private RegistroAsistencia registroAsistencia;
    @FXML
    public void initialize() {
        System.out.println("nombre: Saludos");
        // Inicializar el ObservableList y asignarlo al ListView
        observableList = FXCollections.observableArrayList();
        listView.setItems(observableList);
        // Configurar el ListView con un ListCell personalizado
        listView.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Persona persona, boolean empty) {
                super.updateItem(persona, empty);
                if (empty || persona == null) {
                    setGraphic(null);
                } else {
                    HBox hBox = createHBox(persona);
                    setGraphic(hBox);
                }
            }
        });
    }
    private HBox createHBox(Persona usuario) {
        // Crear un HBox para mostrar dos columnas
        HBox hBox = new HBox(10); // Espaciado entre columnas
        Text nombreText = new Text(String.format("%-20s", usuario.getNombre()));
        // Ajusta el ancho si es  necesario
        Text asisText = new Text(usuario.getEstadoAsis());
        // Patrón de fecha en español
        String pattern = "dd-MM-yyyy HH:mm:ss";
        // SimpleDateFormat con el locale de Perú
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, new Locale("es", "PE"));
        // Formatear la fecha actual
        String formattedDate = simpleDateFormat.format(usuario.fechaReg);
        Text fechaText =new Text(formattedDate);
        hBox.getChildren().addAll(fechaText, nombreText, asisText);
        return hBox;
    }
    @FXML
    private void registrar() throws ParseException {
        observableList.clear();
 /*Persona persona = new Persona();
 persona.setNombre(nombreField.getText());
 persona.setEstadoAsis(asisRegField.getText());*/
        registroAsistencia.registrarAsistencia(new Persona(nombreField.getText(), asisRegField.getText(),
                new Date()));
        System.out.println("Reporte :");
        List<Persona> datos = registroAsistencia.mostrarAsistenciaList();
        // Convertir List<Persona> a ObservableList<Persona>
        ObservableList<Persona> observableListUsuarios = FXCollections.observableArrayList(datos);
        observableList.addAll(datos);
        for (Persona lis : observableListUsuarios) {
            System.out.println("nombre: " + lis.getNombre() + "\t" + "dni: " + lis.getEstadoAsis());
        }
    }
}