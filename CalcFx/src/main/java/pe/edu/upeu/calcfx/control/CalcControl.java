package pe.edu.upeu.calcfx.control;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class CalcControl {

    @FXML
    TextField txtResultado;



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

    }
    public void Binario() {
        try {
            int val = Integer.parseInt(txtResultado.getText());
            txtResultado.setText(Integer.toBinaryString(val));
        } catch (NumberFormatException e) {
            txtResultado.setText("error");
        }

    }
}
