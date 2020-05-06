/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entregable7;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    private Label label;
    @FXML
    private Button btnMostrar;
    @FXML
    private Button btnParar;
    @FXML
    private Label labelHora;
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
    }    
    
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/homePage.fxml"));
        primaryStage.setTitle("Resizing");
        primaryStage.setScene(new Scene(root, 409, 281));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
