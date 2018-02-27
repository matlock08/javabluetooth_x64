package com.roni.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class Main extends Application {
   
    @Override
    public void start(Stage stage) throws java.io.IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/main.fxml"));
               
        final Scene scene = new Scene(root,300, 250);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    public static void main(String[] args) throws java.io.IOException {
        launch(args);
    }
}