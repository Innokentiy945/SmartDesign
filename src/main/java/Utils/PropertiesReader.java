package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    public static String readProperties(String property) {

        try (InputStream input = new FileInputStream("src/test/resources/TestData/Test.properties")) {
            Properties prop = new Properties();
            prop.load(input);
            return (prop.getProperty(property));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
