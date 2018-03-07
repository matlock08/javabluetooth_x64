package com.roni.app;

import  com.roni.config.ApplicationProperties;
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

import org.springframework.boot.*;
import org.springframework.context.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@ComponentScan({"com.roni.service","com.roni.app"})
@SpringBootApplication
@EnableConfigurationProperties({ApplicationProperties.class})
public class Main extends Application {
    private ConfigurableApplicationContext context;
    private Parent rootNode;

    @Override
    public void init() throws Exception {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
        context = builder.run(getParameters().getRaw().toArray(new String[0]));
 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        loader.setControllerFactory(context::getBean);
        rootNode = loader.load();
    }

    @Override
    public void start(Stage stage) throws java.io.IOException {
                       
        final Scene scene = new Scene(rootNode,800, 600);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();
        stage.setFullScreen(true);
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }

    public static void main(String[] args) throws java.io.IOException {
        launch(args);
    }
    
}