package pe.edu.upeu.sysalmacenfx.control;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.sysalmacenfx.componente.AutoCompleteTextField;
import pe.edu.upeu.sysalmacenfx.dto.ModeloDataAutocomplet;
import pe.edu.upeu.sysalmacenfx.servicio.ProductoService;
import pe.edu.upeu.sysalmacenfx.servicio.VentaService;
import win.zqxu.jrviewer.JRViewerFX;

import java.util.SortedSet;
import java.util.TreeSet;

@Component
public class ProdAutoCompControl {

    @Autowired
    ProductoService ps;

    @FXML
    TextField txtBuscarProd, txtPrecio, txtStock;

    @Autowired
    VentaService daoV;
    private JasperPrint jasperPrint;
    @FXML
    BorderPane miReport;

    AutoCompleteTextField actf;
    private final SortedSet<ModeloDataAutocomplet> entries = new TreeSet<>((ModeloDataAutocomplet o1, ModeloDataAutocomplet o2) ->
            o1.toString().compareTo(o2.toString()));

    Stage stage;
    @FXML
    private AnchorPane miContenedor;

    ModeloDataAutocomplet lastProducto;

    @FXML
    public void initialize(){
        Platform.runLater(() -> {
            stage = (Stage) miContenedor.getScene().getWindow();
            System.out.println("El título del stage es: " + stage.getTitle());
        });

        listarProducto();
        actf=new AutoCompleteTextField<>(entries, txtBuscarProd);

        txtBuscarProd.setOnKeyReleased(e->{
            lastProducto=(ModeloDataAutocomplet) actf.getLastSelectedObject();
            if(lastProducto!=null){
                System.out.println(lastProducto.getNameDysplay());
                String[] dato=lastProducto.getOtherData().split(":");
                txtPrecio.setText(dato[0]);
                txtStock.setText(dato[1]);
            }
        });
    }
    public void listarProducto(){
        entries.addAll(ps.listAutoCompletProducto());
    }

    @FXML
    public void mostrarReporte(){
        try {
            jasperPrint= daoV.runReport2();
            JRViewerFX viewerFX = new JRViewerFX(jasperPrint);
            miReport.setCenter(viewerFX);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }


}
