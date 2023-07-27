package krig;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class KrigApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("krig.fxml"));
        stage.setTitle("Krig");
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch(KrigApp.class, args);
    }
}