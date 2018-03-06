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
import org.springframework.stereotype.*;

@Component
public class EntradaController {
    @FXML
	private TextField clock;

    @FXML
	private TextField prompText;

    @FXML
	private StackPane rootPane;


    void initialize() {

    }

    void initData(String customer) {
        prompText.setText("Hola " + customer + " vas a ? ");
    }
    
}