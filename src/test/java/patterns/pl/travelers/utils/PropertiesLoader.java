package patterns.pl.travelers.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static String loadProperty(String propertynName) throws IOException {
        InputStream inputStream = new FileInputStream("src/test/resources/config.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        return properties.getProperty(propertynName);
    }
}
