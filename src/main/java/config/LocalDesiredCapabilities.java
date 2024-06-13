package config;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;

public class LocalDesiredCapabilities {

    public ChromeOptions chrome() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation"));

        // Accept insecure certificates
        options.setAcceptInsecureCerts(true);

        // Set unexpected alert behaviour
        options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);

        options.addArguments("disable-infobars");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");

        // Preferences
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", chromePrefs);

        // Logging preferences
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability("goog:loggingPrefs", logPrefs);

        // W3C arguments (use args instead of switches)
        options.addArguments("--ignore-certificate-errors");

        return options;
    }

}
