package pe.edu.upeu.sysalmacenfx.control;

import jakarta.validation.Validator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.componente.ComboBoxAutoComplete;
import pe.edu.upeu.sysalmacenfx.dto.ComboBoxOption;
import pe.edu.upeu.sysalmacenfx.modelo.Producto;
import pe.edu.upeu.sysalmacenfx.servicio.CategoriaService;
import pe.edu.upeu.sysalmacenfx.servicio.MarcaService;
import pe.edu.upeu.sysalmacenfx.servicio.ProductoService;
import pe.edu.upeu.sysalmacenfx.servicio.UnidadMedidaService;

@Component
public class ProductoController {
    @FXML
    TextField txtNombreProducto, txtPUnit,
            txtPUnitOld, txtUtilidad, txtStock, txtStockOld,
            txtFiltroDato;
    @FXML
    ComboBox<ComboBoxOption> cbxMarca;
    @FXML
    ComboBox<ComboBoxOption> cbxCategoria;
    @FXML
    ComboBox<ComboBoxOption> cbxUnidMedida;
    @FXML
    private TableView<Producto> tableView;
    @FXML
    Label lbnMsg;
    @FXML
    private AnchorPane miContenedor;
    Stage stage;

    @Autowired
    MarcaService ms;
    @Autowired
    CategoriaService cs;
    @Autowired
    ProductoService ps;
    @Autowired
    UnidadMedidaService ums;
    private Validator validator;
    ObservableList<Producto> listarProducto;
    Producto formulario;
    Long idProductoCE=0L;

    public void initialize() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(2000), event -> {
            stage = (Stage) miContenedor.getScene().getWindow();
            if (stage != null) {
                System.out.println("El título del stage es: " + stage.getTitle());
            } else {
                System.out.println("Stage aún no disponible.");
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();

        cbxMarca.setTooltip(new Tooltip());
        cbxMarca.getItems().addAll(ms.listaMarcaCombobox());
        cbxMarca.setOnAction(event -> {
            ComboBoxOption selectedProduct = cbxMarca.getSelectionModel().getSelectedItem();
            if (selectedProduct != null) {
                String selectedId = selectedProduct.getKey(); // Obtener el ID
                System.out.println("ID del producto seleccionado: " + selectedId);
            }
        });
        new ComboBoxAutoComplete<>(cbxMarca);
    }



}
