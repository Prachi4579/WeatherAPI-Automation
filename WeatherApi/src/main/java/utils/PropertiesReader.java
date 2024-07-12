package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesReader.class);

    public static Properties getEndPoint() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/config.properties"))) {
            prop.load(fis);
            logger.info("Loaded config properties: {}", prop);
            return prop;
        } catch (IOException e) {
            logger.error("Failed to load config properties", e);
            return null;
        }
    }

    public static Properties reportMetaData() {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir") + "/src/main/resources/reportmetadata.properties"))) {
            prop.load(fis);
            logger.info("Loaded report metadata properties: {}", prop);
            return prop;
        } catch (IOException e) {
            logger.error("Failed to load report metadata properties", e);
            return null;
        }
    }
}
