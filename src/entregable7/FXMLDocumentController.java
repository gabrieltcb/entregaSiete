/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entregable7;

import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author gabceba
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button btnMostrar;
    @FXML
    private Button btnParar;
    @FXML
    private Label lblHora;
    
    int horas, minutos, segundos;
    Calendar calendario;
    @FXML
    private Label lblEstado;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        calendario = Calendar.getInstance();
        horas = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);

        Service<String> service = new Service<String>() {
//            long accumulated = 0;
            long begin;

            @Override
            protected Task<String> createTask() {
                return new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        begin = System.currentTimeMillis();
                        while (true) {
                            calendario = Calendar.getInstance();
                            long time = System.currentTimeMillis();
                            updateValue(String.format("%02d:%02d:%02d",
                                horas = calendario.get(Calendar.HOUR_OF_DAY),
                                minutos = calendario.get(Calendar.MINUTE),
                                segundos = calendario.get(Calendar.SECOND)
                            ));
                            Thread.sleep(1);
                        }
                    }
                };
            }

            @Override
            protected void cancelled() {
//                accumulated = System.currentTimeMillis() - begin + accumulated;
                super.cancelled();
            }
        };
        
        btnParar.setDisable(true);
        lblHora.textProperty().bind(service.valueProperty());
    
        btnMostrar.setOnAction(event -> {
            service.reset();
            lblEstado.setText("Funcionando.");

            btnMostrar.setDisable(true);
            btnParar.setDisable(false);

            service.start();
        });
        
        btnParar.setOnAction(event -> {
            lblEstado.setText("Parado en:");
            
            btnMostrar.setDisable(false);
            btnParar.setDisable(true);
            
            service.cancel();
        });
        
//        btnMostrar.visibleProperty().bind(service.runningProperty());
    }  

        
        
    @FXML
    private void mostrarHora(ActionEvent event) {
    }

    @FXML
    private void pararHora(ActionEvent event) {
    }

}