package com.roni.app;

import com.roni.blue.*;
import com.roni.service.*;

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
import org.springframework.beans.factory.annotation.*;
import com.machinezoo.sourceafis.*;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

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

    @Autowired
    private BackendService service;

    private String btURL = "btspp://881B9911B3EE:6;authenticate=false;encrypt=false;master=false";
    private Timer myTimer;
    private OneSecTimerTask oneSecTimerTask;
    private EmpleadoResponse empleado;

    public void initialize() {
        oneSecTimerTask = new OneSecTimerTask(clock);
        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(oneSecTimerTask, 0, 1000);
    }

    void initData(EmpleadoResponse empleado) {
        this.empleado = empleado;
        prompText.setText("Hola " + empleado.getNombre() + " vas a ? ");
        java.util.Calendar cal = java.util.Calendar.getInstance();
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
        clock.setText( sdf.format(cal.getTime()) );
        try {
            clientBT = new RFCommClient(btURL);
        } catch( java.io.IOException | java.lang.InterruptedException ioe  ) {
            counter.setText( "Unable to connect to BT" );
        }
    }

    @FXML
    private void handleEntryAction(ActionEvent event) {
        if ( executeAction("1") ) {
            System.out.println("Se registro entrada");
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

    private boolean executeAction(String action) {
        boolean res = false;

        try {
            byte[]templateDevice = clientBT.captureHost(5000);
            FingerprintTemplate templateRequest = new FingerprintTemplate(templateDevice);
            
            String token = service.getToken().getId_token();
            Set<FingerPrintRequest> huellas = empleado.getHuellas();

            for(FingerPrintRequest huella : huellas ) {
                FingerprintMatcher matcher = new FingerprintMatcher(new FingerprintTemplate(huella.getTemplate()));
                double score = matcher.match(templateRequest);

                if ( score>= 40 ) {
                    if ( service.registerEmpleadoAction(String.valueOf(empleado.getId()),action, token) ) {
                        return true;
                    }
                }
            }

        } catch( java.io.IOException ioe ) {
            res = false;
            counter.setText( "Unable to connect to BT" );
        }
        

        return res;
    }
    
}