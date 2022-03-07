import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader=new FXMLLoader(getClass().getResource("GUI.fxml"));
        Parent root=loader.load();
        Scene scene=new Scene(root);
        scene.getStylesheets().add("styling.css");
        primaryStage.setScene(scene);
        primaryStage.setTitle("Welcome");
        primaryStage.setResizable(false);
        primaryStage.show();


    }
    public static void main(String[] args) {

        launch(args);
    }
}
