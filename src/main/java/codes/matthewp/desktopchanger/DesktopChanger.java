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

import java.io.File;
import java.util.*;

public class DesktopChanger extends Application {

    private static Configuration config;

    public static void main(String[] args) {
        config = new Configuration();
        startTimer();
        startTimer();
        launch(args);
    }

    private static void startTimer() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            // TODO: Inefficent af
            @Override
            public void run() {

                File imgFolder = new File(config.imagePath);
                File[] rawFiles = imgFolder.listFiles();
                List<File> backgrounds = new ArrayList<>();

                for (File f : rawFiles) {
                    if (getFileExtension(f).equals("jpg") || getFileExtension(f).equals("png") || getFileExtension(f).equals("jpeg")) {
                        backgrounds.add(f);
                    }
                }

                int ran = new Random().nextInt(backgrounds.size());
                setBackground(backgrounds.get(ran).getAbsolutePath());
            }
        }, 0L, config.determineTimer());
    }

    public static void setBackground(String path) {
        SPI.INSTANCE.SystemParametersInfo(
                new WinDef.UINT_PTR(SPI.SPI_SETDESKWALLPAPER),
                new WinDef.UINT_PTR(0),
                path,
                new WinDef.UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
    }

    private static String getFileExtension(File file) {
        int i = file.getName().lastIndexOf('.');
        if (i > 0) {
            return file.getName().substring(i + 1);
        }
        return "";
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        Platform.setImplicitExit(false);
    }
}
