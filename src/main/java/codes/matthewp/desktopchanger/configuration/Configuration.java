package codes.matthewp.desktopchanger.configuration;

import codes.matthewp.desktopchanger.io.FileUtil;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {

    private Properties prop;

    private File configFolder = new File("config/");
    private File configFile = new File("config/main.config");

    public Configuration() {
        prop = new Properties();
        testIntegrity();
        load();

        System.out.println(prop.getProperty("timer.type"));
        System.out.println(prop.getProperty("timer.timeint"));
    }

    private void load() {
        InputStream is = null;
        try {
            is = new FileInputStream(configFile);
            prop.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void testIntegrity() {
        if(!configFile.exists()) {
            configFolder.mkdir();
            try {
                configFile.createNewFile();
                FileUtil.copyFile(new File(getClass().getResource("/config/default.config").getFile()), configFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
