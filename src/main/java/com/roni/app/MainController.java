package com.roni.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MainController {
    @FXML
	private Button myOkButton;

    @FXML
	private TextField clock;

    @FXML
	private StackPane rootPane;

    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Action " +  event );

        loadNextScene();
    }

    private void loadNextScene() {
        try {
            Parent entradaView = (StackPane)FXMLLoader.load(getClass().getResource("/fxml/entrada.fxml"));
                
            final Scene entradaScene = new Scene(entradaView);

            Stage currStage = (Stage)rootPane.getScene().getWindow();

            currStage.setScene(entradaScene);
        } catch(java.io.IOException ioe ) {

        }

    }
    
}