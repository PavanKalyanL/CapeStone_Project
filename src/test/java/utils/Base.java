package utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Base {
    public Properties prop;
    public static WebDriver drive = null;

    public void launchBrowser() {
        prop = PropertyReader.readProperty();
        String browserName = prop.getProperty("Browser");

        if (browserName.equalsIgnoreCase("Chrome")) {

            // disable password manager popups
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_leak_detection", false);

            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);

            drive = new ChromeDriver(options);

        } else if (browserName.equalsIgnoreCase("FireFox")) {
        	 Map<String, Object> prefs = new HashMap<>();
             prefs.put("credentials_enable_service", false);
             prefs.put("profile.password_manager_leak_detection", false);

             FirefoxOptions options = new FirefoxOptions();

             drive = new FirefoxDriver(options);
            drive = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("Edge")) {
            drive = new EdgeDriver();
        } else {
            System.out.println("Invalid Browser");
        }

        drive.manage().window().maximize();
    }

    public void url() {
        String url = prop.getProperty("URL");
        drive.get(url);
    }
}