package com.roni.app;

import com.roni.service.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;

@Component
public class MainController {
    @FXML
	private Button myOkButton;

    @FXML
	private Button myCancelButton;

    @FXML
	private TextField clock;

    @FXML
	private TextField numeroEmpleado;

    @FXML
	private StackPane rootPane;

    @Autowired
    private BackendService service;

    private boolean first = true;

    
    @FXML
    private void handleEnterAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Action " +  event );
        String token = service.getToken().getId_token();
        System.out.println( "Token " + token );

        System.out.println( service.getEmpleadoById("1351", token ).getNombre() );

        loadNextScene();
    }

    @FXML
    private void handleClearAction(ActionEvent event) {
        
        
        numeroEmpleado.setText("");
        
    }

    @FXML
    private void handleDigitAction(ActionEvent event) {
        
        if (first) {
            first = false;
            
            numeroEmpleado.setText(((Button)event.getSource()).getText());
        } else {
            
            numeroEmpleado.appendText(((Button)event.getSource()).getText());
        }
        
    }

    private void loadNextScene() {
        try {
            String nombreEmpleado = ""; //getEmpleadoById(numeroEmpleado.getText());

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/entrada.fxml"));
            Scene entradaScene = new Scene((Pane)loader.load());
            Stage currStage = (Stage)rootPane.getScene().getWindow();
            EntradaController controller = loader.<EntradaController>getController();
            controller.initData( nombreEmpleado );
            currStage.setScene(entradaScene);

        } catch(java.io.IOException ioe ) {

        }

    }

    private String getEmpleadoById(String id) {
        return service.getEmpleadoById(id, "").getNombre();
    }
    
}