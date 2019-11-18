package com.roni.app;

import com.roni.service.*;
import com.roni.task.*;
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
import org.springframework.context.ApplicationContext;
import java.util.Timer;
import java.util.TimerTask;

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

    @Autowired
    private ApplicationContext context;

    private boolean first = true;
    private Timer myTimer;
    private OneSecTimerTask oneSecTimerTask;

    public void initialize() {
        oneSecTimerTask = new OneSecTimerTask(clock);
        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(oneSecTimerTask, 0, 1000);
    }

    
    @FXML
    private void handleEnterAction(ActionEvent event) {
        
        if ( !"".equals(numeroEmpleado.getText()) ) {
            loadNextScene();
        }
        
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
            String token = ""; //service.getToken().getId_token();
            EmpleadoResponse empleado =  new Empleado(); //service.getEmpleadoById(numeroEmpleado.getText(), token );
            
            if ( empleado != null ) {            
                myTimer.cancel();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/entrada.fxml"));
                loader.setControllerFactory(context::getBean);
                Scene entradaScene = new Scene((Pane)loader.load());
                Stage currStage = (Stage)rootPane.getScene().getWindow();
                EntradaController controller = loader.<EntradaController>getController();
                controller.initData( empleado );
                currStage.setScene(entradaScene);
                currStage.setFullScreen(true);
            }

        } catch(java.io.IOException ioe ) {
            System.out.println(ioe);
        }

    }
    
}