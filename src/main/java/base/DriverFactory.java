package base;

import com.google.common.io.Files;
import config.LocalDesiredCapabilities;
import enums.TestProperties;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static enums.TestProperties.BROWSER;
import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.SAFARI;


public class DriverFactory {
    private static final Logger logger = LoggerFactory.getLogger(DriverFactory.class.getSimpleName());

    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();
    public static WebDriverWait webDriverWait;
    private static WebDriver driver;
    private static Properties properties;
    public String baseUrl;

    public DriverFactory(Properties properties) {
        DriverFactory.properties = properties;
    }

    public static WebDriverWait getWebDriverWait() {
        return webDriverWait;
    }

    public WebDriver getDriver() {
        LocalDesiredCapabilities localDesiredCapabilities = new LocalDesiredCapabilities();
        WebDriverListener listener = getWebDriverListener();

        String browser = properties.getProperty(BROWSER, CHROME);
        // Переиспользование браузера тестов в том же потоке
        if (threadLocalDriver.get() != null) {
            driver = threadLocalDriver.get();
        }

        if (driver == null) {
            switch (browser) {
                case "ff":
                case "firefox":
                    /**
                     * Solution
                     * Upgrade JDK to recent levels JDK 8u191.
                     * Upgrade Selenium to current levels Version 3.14.0.
                     * Upgrade GeckoDriver to GeckoDriver v0.23.0 level.
                     * GeckoDriver is present in the specified location.
                     * GeckoDriver is having executable permission for non-root users.
                     * Upgrade Firefox version to Firefox v63.0.1 levels.
                     * Clean your Project Workspace through your IDE and Rebuild your project with required dependencies only.
                     * If your base Web Client version is too old, then uninstall it through Revo Uninstaller and install a recent GA and released version of Web Client.
                     * Take a System Reboot.
                     * Execute your Test as a non-root user.
                     * Always invoke driver.quit() within tearDown(){} method to close & destroy the WebDriver and Web Client instances gracefully.
                     */
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setCapability("marionette", true);
                    firefoxOptions.setBinary(properties.getProperty(TestProperties.WEBDRIVER_GECKO_BINARY));
                    System.setProperty(TestProperties.GECKO_DRIVER, properties.getProperty(TestProperties.GECKO_DRIVER_PATH));
                    driver = new EventFiringDecorator(listener).decorate(new FirefoxDriver(firefoxOptions));
                    threadLocalDriver.set(driver);

                    break;
                case CHROME:
                case "googlechrome":
                case "gc":
                    // chmod a+x
                    // xattr -d com.apple.quarantine chromedriver
                    // File file = new File("src/test/resources/drivers/chromedriver");
                    File chromeDriver = new File(properties.getProperty(TestProperties.CHROME_DRIVER_PATH));
                    System.setProperty(TestProperties.CHROME_DRIVER, chromeDriver.getAbsolutePath());
                    /*try {
                        driver = new RemoteWebDriver(new URL("http://192.168.0.11:4444/wd/hub"), localDesiredCapabilities.chrome());
                        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }*/
                    driver = new EventFiringDecorator(listener).decorate(new ChromeDriver(localDesiredCapabilities.chrome()));
                    threadLocalDriver.set(driver);
                    break;
                case SAFARI:
                    driver = new EventFiringDecorator(listener).decorate(new SafariDriver());
                    threadLocalDriver.set(driver);

                    break;
                default:
                    throw new Error("Unsupported Browser");
            }

            assert driver != null;
            webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            /*Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(new Function<WebDriver, Boolean>() {
                public Boolean apply(WebDriver driver) {
                    System.out.println("Current Window State       : "
                            + String.valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState")));
                    return String
                            .valueOf(((JavascriptExecutor) driver).executeScript("return document.readyState"))
                            .equals("complete");
                }
            });*/
            //PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
            //driver.get(properties.getProperty(enums.TestProperties.BASE_URL));
            //System.out.println(driver.getTitle());
        }
        /*Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { driver.quit(); driver = null; }));*/
        return driver;
    }

    private WebDriverListener getWebDriverListener() {
        WebDriverListener listener = new WebDriverListener() {
            @Override
            public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
                File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                try {
                    Files.copy(tempFile, new File("screen" + System.currentTimeMillis() + ".png"));
                } catch (IOException ioException) {
                    ioException.printStackTrace(System.err);
                    logger.info(ioException.getMessage());
                }
                //throw new RuntimeException("listener");
            }
        };
        return listener;
    }


}
