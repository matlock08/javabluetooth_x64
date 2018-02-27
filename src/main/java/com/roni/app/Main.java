package rom.roni.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Button enrol = new Button("Enrol");
        Button capture = new Button("Capture");
        
        
        HBox box = new HBox();
        box.getChildren().add(enrol);
        box.getChildren().add(capture);
        
                
        final Scene scene = new Scene(box,300, 250);
        scene.setFill(null);
        stage.setScene(scene);
        stage.show();

        stage.setFullScreen(true);
    }

    public static void main(String[] args) {
        launch(args);
    }
}