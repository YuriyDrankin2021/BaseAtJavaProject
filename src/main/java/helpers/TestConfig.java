package helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestConfig {

    private TestConfig() {

    }

    private static final Logger logger = LoggerFactory.getLogger(TestConfig.class);

    public static Properties getPropertiesForEnv(String env) {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream("src/test/resources/env/" + env + "/" + env + ".properties")) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            logger.debug(e.getMessage());
        }
        return properties;
    }

    public static Properties config() {
        String env = System.getProperty("env");
        return getPropertiesForEnv(env);
    }

    public static String getProperty(String name) {
        Properties properties = config();
        return System.getProperty(name) == null ? properties.getProperty(name) : System.getProperty(name);
    }
}
