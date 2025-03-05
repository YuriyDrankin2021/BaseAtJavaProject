package helpers;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

import static helpers.TestConfig.getProperty;

public class Browser {

    public static void setDriver() {
        setDriver(getProperty("browser"));
    }

    public static void setDriver(String driver) {
        boolean remote = Boolean.parseBoolean(getProperty("remote"));
        if (remote) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            switch (driver) {
                case "chrome":
                    Configuration.browser = "chrome";
                    Configuration.browserVersion = "128.0";
                    break;
                case "opera":
                    Configuration.browser = "opera";
                    Configuration.browserVersion = "109.0";
                    break;
                case "firefox":
                    Configuration.browser = "firefox";
                    Configuration.browserVersion = "124.0";
            }
            capabilities.setCapability("acceptInsecureCerts", true);
            Configuration.screenshots = true;
            Configuration.browserCapabilities = capabilities;
            Configuration.browserSize = "1920x1080";
            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("enableVideo", false);
            selenoidOptions.put("name", "BaseAt");
            selenoidOptions.put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});
            capabilities.setCapability("selenoid:options", selenoidOptions);
            Configuration.remote = "http://localhost:4444/wd/hub";
        }
        Configuration.headless = Boolean.parseBoolean(getProperty("headless"));
    }
}
