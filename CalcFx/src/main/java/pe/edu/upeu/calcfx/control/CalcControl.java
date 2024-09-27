package pe.edu.upeu.calcfx.control;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pe.edu.upeu.calcfx.modelo.CalcTO;
import pe.edu.upeu.calcfx.service.CalcServiceI;

import java.util.List;

@Component
public class CalcControl {

    @Autowired
    CalcServiceI serviceI;

    @FXML
    TextField txtResultado;

    @FXML
    TableView tableView ;
    private ObservableList<CalcTO> calcTOList;

    private int indexEdit=-1;

    @FXML
    TableColumn <CalcTO, String> cVal1,cVal2,cResult;

    @FXML
    TableColumn <CalcTO, Character> cOper;

    @FXML
    TableColumn <CalcTO, Void> cOpc;




    @FXML
    public void accionButton(ActionEvent event){
        Button button= (Button)event.getSource();
        switch (button.getId()){
            case "btn7","btn8","btn9","btn6","btn5","btn4","btn3","btn2","btn1","btn0":{
                escribirNumeros(button.getText());
            }break;
            case "btnSum", "btnMul", "btnRest", "btnDiv":{
                operador(button.getText());
            }break;
            case "btnIgual":{
                calcularResultado();
            }break;
            case "btnBorrar":{
                txtResultado.clear();
            }break;
            case "btnRaiz":{
                calcularRaiz();
            } break;
            case "btnCuad":{
                calcularCuadrado();
            }break;
            case "btnPi":{
                mostrarPi();

            }
            case "btnBinario":{
                Binario();

            }


        }

    }

    public void escribirNumeros(String valor){
        txtResultado.appendText(valor);

    }

    public void operador(String valor){
        txtResultado.appendText(" "+valor+" ");
    }
    public void calcularRaiz() {
        try {
            double val = Double.parseDouble(txtResultado.getText());
            txtResultado.setText(String.valueOf(Math.sqrt(val)));
        } catch (NumberFormatException e) {
            txtResultado.setText("error");
        }
    }
    public void calcularCuadrado() {
        try {
            double val = Double.parseDouble(txtResultado.getText());
            txtResultado.setText(String.valueOf(Math.pow(val, 2)));
        } catch (NumberFormatException e) {
            txtResultado.setText("error");
        }
    }
    public void mostrarPi(){
        txtResultado.setText(String.valueOf(Math.PI));
    }

    public  void calcularResultado(){
        String[] valores=txtResultado.getText().split(" ");
        double val1=Double.parseDouble(String.valueOf(valores[0]));
        double val2=Double.parseDouble(String.valueOf(valores[2]));
        switch (valores[1]){
            case "+":{
                txtResultado.setText(String.valueOf(val1+val2));
            }break;
            case "-":{
                txtResultado.setText(String.valueOf(val1-val2));
            }break;
            case "/":{
                if (val2!=0){
                    txtResultado.setText(String.valueOf(val1/val2));
                }else {
                    txtResultado.setText("Error");
                }

            }break;
            case "*":{
                txtResultado.setText(String.valueOf(val1*val2));
            }break;

        }
        CalcTO to=new CalcTO();
        to.setId(Long.parseLong(String.valueOf(indexEdit)));
        to.setNum1(String.valueOf(val1));
        to.setNum2(String.valueOf(val2));
        to.setOperador(valores[1].charAt(0));
        to.setResultado(String.valueOf(txtResultado.getText()));
        if(indexEdit!=-1){
            serviceI.actualizarResultados(to, to.getId());
        }else{
            serviceI.guardarResultados(to);
        }
        indexEdit=-1;
        listaOper();




    }


    private void editOperCalc(CalcTO cal, Long index) {
        System.out.println("Editing: " + cal.getNum1() + " Index:"+index);
        txtResultado.setText(cal.getNum1()+" "+cal.getOperador()+" "+cal.getNum2());
                indexEdit=index.intValue();
    }

    private void deleteOperCalc(CalcTO cal,Long index) {
        System.out.println("Deleting: " + cal.getNum2());
        serviceI.eliminarResultados(index);
        listaOper();
        //tableView.getItems().remove(cal);  // Elimina la operación de TableView
    }


    public void Binario() {
        try {
            int val = Integer.parseInt(txtResultado.getText());
            txtResultado.setText(Integer.toBinaryString(val));
        } catch (NumberFormatException e) {
            txtResultado.setText("error");
        }

    }

    private void addActionButtonsToTable() {
        Callback<TableColumn<CalcTO, Void>, TableCell<CalcTO, Void>>
                cellFactory = param -> new TableCell<>() {

            private final Button editButton = new Button("Edit");
            private final Button deleteButton = new Button("Delete");

            {
                editButton.getStyleClass().setAll("btn", "btn-success");
                editButton.setOnAction(event -> {
                    CalcTO cal = getTableView().getItems().get(getIndex());
                    editOperCalc(cal, cal.getId());
                });

                deleteButton.getStyleClass().setAll("btn", "btn-danger");
                deleteButton.setOnAction(event -> {
                    CalcTO cal = getTableView().getItems().get(getIndex());
                    deleteOperCalc(cal,cal.getId());
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    HBox buttons = new HBox(editButton, deleteButton);
                    buttons.setSpacing(10);
                    setGraphic(buttons);
                }
            }
        };
        cOpc.setCellFactory(cellFactory);
    }

    public void  listaOper(){
        List<CalcTO> lista=serviceI.obtenerResultados();
        for (CalcTO to:lista) {
            System.out.println(to.toString());
        }
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Vincular columnas con propiedades de CalcTO
        cVal1.setCellValueFactory(new PropertyValueFactory<CalcTO,
                        String>("num1"));

        cVal1.setCellFactory(TextFieldTableCell.<CalcTO>forTableColumn());

        cVal2.setCellValueFactory(new PropertyValueFactory<CalcTO,
                String>("num2"));

        cVal2.setCellFactory(TextFieldTableCell.<CalcTO>forTableColumn());

        cOper.setCellValueFactory(new
                PropertyValueFactory<>("operador"));
        cOper.setCellFactory(ComboBoxTableCell.<CalcTO,
                Character>forTableColumn('+', '-', '/', '*'));

        cResult.setCellValueFactory(new PropertyValueFactory<CalcTO,
                String>("resultado"));

        cResult.setCellFactory(TextFieldTableCell.<CalcTO>forTableColumn());

        // Agregar botones de eliminar y modificar
        addActionButtonsToTable();
        calcTOList = FXCollections.observableArrayList(serviceI.obtenerResultados());
        // Asignar los datos al TableView
        AnchorPane.setLeftAnchor(tableView, 0.0);
        AnchorPane.setRightAnchor(tableView, 0.0);

        cOper.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cResult.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25)); // 25% del ancho total

        cOpc.prefWidthProperty().bind(tableView.widthProperty().multiply(0.25));
        tableView.setItems(calcTOList);
    }
}
