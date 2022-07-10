package base;

import config.PropertyReader;
import enums.TestProperties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import steps.SearchSteps;

import java.util.Properties;

import static enums.TestProperties.MAXIMIZE;

public abstract class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class.getSimpleName());
    public static WebDriverWait wait;
    public static Properties properties = new Properties();
    private static DriverFactory browserController;
    private static WebDriver driver;
    public static SearchSteps steps;

    public static String getProperty(String propertyName) {
        if (System.getProperty(propertyName) == null) {
            return properties.getProperty(propertyName);
        } else {
            System.getProperty(propertyName);
        }
        return propertyName;
    }

    public static String getBaseUrl() {
        return getProperty("url");
    }

    @BeforeTest
    public void setUp() throws Exception {
        properties = PropertyReader.loadProperties();
        browserController = new DriverFactory(properties);
        driver = browserController.getDriver();
        steps = new SearchSteps();

        if (Boolean.parseBoolean(properties.getProperty(MAXIMIZE, "false"))) driver.manage().window().maximize();

        wait = DriverFactory.getWebDriverWait();
        //driver.register(new EventListener());


//      BrowserMobProxy proxy = new BrowserMobProxyServer();
//      proxy.start(0);
//      int port = proxy.getPort(); // get the JVM-assigned port
//      Selenium or HTTP client configuration goes here
        driver.navigate().to(properties.getProperty(TestProperties.URL));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input"))));
    }

    @AfterTest
    void closeBrowser() {
        // driver.close(); // closes 1 windows, process might be opened even if browser was closed.
        this.getDriver().quit(); // close browser opened by selenium, closes all windows and clears the memory
    }

    public WebDriver getDriver() {
        return browserController.getDriver();
    }


}
