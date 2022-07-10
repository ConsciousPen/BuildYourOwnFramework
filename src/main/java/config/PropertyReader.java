package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    private static final String PATH = "src/test/resources/config/project.properties";

    public static String getPropertyFromFile(String propertyName) {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("Cannot read property '" + propertyName + "'");
            e.printStackTrace();
        }
        return properties.getProperty(propertyName);
    }

    public static Properties loadProperties() {
        Properties properties = new Properties();
        InputStream inputStream = null;

        try {
            inputStream = new FileInputStream(PATH);
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
