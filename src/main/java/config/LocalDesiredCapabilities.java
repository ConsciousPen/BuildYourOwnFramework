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
        options.addArguments("disable-infobars", "--no-sandbox");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability("chrome.switches", Collections.singletonList("--ignore-certificate-errors"));
        options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
        // Preferences
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("safebrowsing.enabled", true);
        chromePrefs.put("profile.default_content_settings.popups", 0);
        chromePrefs.put("download.prompt_for_download", "false");
        chromePrefs.put("plugins.always_open_pdf_externally", true);
        options.setExperimentalOption("prefs", chromePrefs);
        // logs
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        return options;
    }

    public FirefoxOptions firefox(FirefoxOptions options) {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/ms-excel");
        options.setCapability(FirefoxDriver.Capability.PROFILE, profile);
        return options;
    }

    public InternetExplorerOptions iexplore(InternetExplorerOptions options) {
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        options.usePerProcessProxy();
        options.setCapability("requireWindowFocus", false);
        options.setCapability("ie.ensureCleanSession", true);
        options.setCapability("ie.browserCommandLineSwitches", Arrays.asList("-private"));
        options.takeFullPageScreenshot();
        options.ignoreZoomSettings();
        options.introduceFlakinessByIgnoringSecurityDomains();
        return options;
    }

    public SafariOptions safari() {
        SafariOptions options = new SafariOptions();

        return options;
    }
}
