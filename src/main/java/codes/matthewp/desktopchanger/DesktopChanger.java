package codes.matthewp.desktopchanger;

import codes.matthewp.desktopchanger.configuration.Configuration;
import codes.matthewp.desktopchanger.core.SPI;
import com.sun.jna.platform.win32.WinDef;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DesktopChanger extends Application {

    private static Configuration config;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Platform.setImplicitExit(false);
    }

    public static void main(String[] args) {
        config = new Configuration();
        setBackground("C:\\Users\\Matthew\\Downloads\\imageproxy (2).jpg");
        launch(args);
    }

    public static void setBackground(String path) {
        SPI.INSTANCE.SystemParametersInfo(
                new WinDef.UINT_PTR(SPI.SPI_SETDESKWALLPAPER),
                new WinDef.UINT_PTR(0),
                path,
                new WinDef.UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
    }
}
