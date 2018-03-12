package com.roni.app;

import com.roni.blue.*;

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
	private TextField counter;

    @FXML
	private TextField prompText;

    @FXML
	private StackPane rootPane;

    private RFCommClient clientBT;


    void initialize() {
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        clock.setText( sdf.format(cal.getTime()) );
        try {
            clientBT = new RFCommClient(null);
        } catch( java.io.IOException | java.lang.InterruptedException ioe  ) {
            counter.setText( "Unable to connect to BT" );
        }
    }

    void initData(String customer) {
        prompText.setText("Hola " + customer + " vas a ? ");
    }

    @FXML
    private void handleEntryAction(ActionEvent event) {
        try {
            clientBT.captureHost(5000);
        } catch( java.io.IOException ioe ) {
            counter.setText( "Unable to connect to BT" );
        }
        
    }

    @FXML
    private void handleOutForLunchAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Action " +  event );

        
    }

    @FXML
    private void handleComeBackLunchAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Action " +  event );

        
    }

    @FXML
    private void handleEndOfDayAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Action " +  event );

        
    }

    @FXML
    private void handleAnotherExitAction(ActionEvent event) {
        // Button was clicked, do something...
        System.out.println("Button Action " +  event );

        
    }
    
}