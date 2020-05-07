/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entregable7;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

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
    javafx.util.Duration dr = new javafx.util.Duration(0.0);
    @FXML
    private Button btnResetear;
    @FXML
    private Label lblPausado;
    String pausadoEn = "";
    
    
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
                            pausadoEn = "Pausado en: " + (String.format("%02d:%02d:%02d",
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
        
        btnMostrar.setVisible(true);
        btnResetear.setVisible(false);
        btnParar.setVisible(false);

        lblHora.textProperty().bind(service.valueProperty());
    
        btnMostrar.setOnAction(event -> {
            btnMostrar.setVisible(false);
            lblPausado.setVisible(false);
            btnParar.setVisible(true);
            service.start();
        });
        
        btnParar.setOnAction(event -> {
            btnResetear.setVisible(true);
            btnParar.setVisible(false);
            service.cancel();
        });
        
        btnResetear.setOnAction(event -> {
            btnMostrar.setVisible(true);
            btnResetear.setVisible(false);
            lblPausado.setVisible(true);
            lblPausado.setText("00:00:00");
            service.reset();
        });

//        btnResetear.visibleProperty().bind(service.runningProperty());
    }  

        
        
    @FXML
    private void mostrarHora(ActionEvent event) {
    }

    @FXML
    private void pararHora(ActionEvent event) {
    }

    @FXML
    private void reiniciarHora(ActionEvent event) {
    }
}