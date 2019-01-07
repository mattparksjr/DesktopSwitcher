package codes.matthewp.desktopchanger;

import codes.matthewp.desktopchanger.configuration.Configuration;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesktopChanger extends Application {

    private static Configuration config;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Platform.setImplicitExit(false);
    }


    public static void main(String[] args) {
        config = new Configuration();
        launch(args);
    }
}
