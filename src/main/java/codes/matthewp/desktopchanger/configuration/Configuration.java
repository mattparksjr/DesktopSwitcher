package codes.matthewp.desktopchanger.configuration;

import codes.matthewp.desktopchanger.io.FileUtil;

import java.io.*;
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

    /**
     * Used to read the config and determine the configured time setting
     * @return Time setting in milliseconds
     */
    public int determineTimer() {
        String timerType = prop.getProperty("timer.type");
        int timeInterval = Integer.valueOf(prop.getProperty("timer.timeint"));

        if (timerType.equalsIgnoreCase("s")) {
            timeInterval = timeInterval * 1000;
        } else if (timerType.equalsIgnoreCase("m")) {
            timeInterval = timeInterval * 60 * 1000;
        } else if (timerType.equalsIgnoreCase("h")) {
            timeInterval = timeInterval * 60 * 60 * 1000;
        } else {
            // 5 minutes is the default time setting
            timeInterval = 5 * 60 * 1000;
        }
        return timeInterval;
    }

    private void load() {
        InputStream is;
        try {
            is = new FileInputStream(configFile);
            prop.load(is);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void testIntegrity() {
        if (!configFile.exists()) {
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
