/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entregable7;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
    private Label labelHora;
    @FXML
    private Label testLabel;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
//    https://stackoverflow.com/questions/13246211/javafx-how-to-get-stage-from-controller-during-initialization/30910015
//    https://stackoverflow.com/questions/29729987/javafx-need-help-seting-stage-upon-initialize
//    https://stackoverflow.com/questions/34809447/disable-maximize-button-and-resizing-window-in-javafx/47612391
    
//    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("views/homePage.fxml"));
//        primaryStage.setTitle("Resizing");
//        primaryStage.setScene(new Scene(root, 409, 281));
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }
    
    
    @FXML
    private void mostrarHora(ActionEvent event) {
        timer.start();
    }

    @FXML
    private void pararHora(ActionEvent event) {
        timer.stop();
    }
    
    AnimationTimer timer = new AnimationTimer() {
        private long timestamp;
        private long time = 0;
        private long fraction = 0;

        private LocalTime horaSys;
        private String testdos;
        DateTimeFormatter dtf;
        
        
        @Override
        public void start() {
            // current time adjusted by remaining time from last run
            timestamp = System.currentTimeMillis() - fraction;
            
            dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
            horaSys = LocalTime.now();
            super.start();
        }

        @Override
        public void stop() {
            super.stop();
            // save leftover time not handled with the last update
            fraction = System.currentTimeMillis() - timestamp;
        }

        @Override
        public void handle(long now) {
            long newTime = System.currentTimeMillis();
            if (timestamp + 1000 <= newTime) {
                long deltaT = (newTime - timestamp) / 1000;
                time += deltaT;
                timestamp += 1000 * deltaT;
                labelHora.setText(Long.toString(time));
                testLabel.setText(horaSys.format(dtf));
            }
        }
    };

}
