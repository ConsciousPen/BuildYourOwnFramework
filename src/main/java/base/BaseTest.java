package base;

import config.PropertyReader;
import enums.TestProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import steps.SearchSteps;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.logging.Level;

import static enums.TestProperties.MAXIMIZE;

public abstract class BaseTest implements HasPriority {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class.getSimpleName());

    public static final String RESOURCES_FOLDER = "src/test/resources/";
    public static final String configFile = "src/test/resources/config/local.properties";

    public static WebDriverWait wait;
    public static SearchSteps steps;
    public static Properties properties;
    private static BrowserController browserController;

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

    public static WebElement findElement(By by) {
        WebElement elem = BrowserController.getDriver().findElement(by);
        // draw a border around the found element
        if (BrowserController.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) BrowserController.getDriver()).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
    }

    public void waitElementPresence(By xpath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(xpath));
    }

    public void waitElementInvisibility(By xpath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xpath));
    }

    public List<WebElement> findElements(String stringXpath) {
        List<WebElement> elements = BrowserController.getDriver().findElements(By.xpath(stringXpath));
        if (BrowserController.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) BrowserController.getDriver()).executeScript("arguments[0].style.border='3px solid red'", elements);
        }
        return elements;
    }

    /**
     * Closes the alert and returns its text.
     *
     * @param accept - true to accept the alert, false to dismiss it
     * @return The text of the alert, or null if no alert is present
     */
    public String closeAlertAndGetItsText(boolean accept) {
        try {
            Alert alert = BrowserController.getDriver().switchTo().alert();
            String alertText = alert.getText();
            if (accept) {
                alert.accept();
                logger.info("Alert accepted: {}", alertText);
            } else {
                alert.dismiss();
                logger.info("Alert dismissed: {}", alertText);
            }
            return alertText;
        } catch (NoAlertPresentException e) {
            logger.error("No alert present to close", e);
            return null;
        }
    }

    public boolean isElementPresent(By by) {
        try {
            findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isAlertPresent() {
        try {
            BrowserController.getDriver().switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    protected void type(By locator, String text) {
        if (text != null) {
            findElement(locator).clear();
            findElement(locator).sendKeys(text);
        }

    }

    public static void waitForPageLoad() {
        FluentWait<WebDriver> wait = new FluentWait<>(BrowserController.getDriver())
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);

        Function<WebDriver, Boolean> pageLoadCondition = new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };

        wait.until(pageLoadCondition);
    }

    @BeforeTest
    public void setUp() throws Exception {
        browserController = new BrowserController();
        properties = BrowserController.getProperties();
//      steps = new SearchSteps();

        if (Boolean.parseBoolean(BrowserController.getProperties().getProperty(MAXIMIZE, "false"))) BrowserController.getDriver().manage().window().maximize();

        wait = BrowserController.getWebDriverWait();
        //driver.register(new EventListener());


//      BrowserMobProxy proxy = new BrowserMobProxyServer();
//      proxy.start(0);
//      int port = proxy.getPort(); // get the JVM-assigned port
//      Selenium or HTTP client configuration goes here

        BrowserController.getDriver().navigate().to(BrowserController.getProperties().getProperty(TestProperties.URL));
        waitForPageLoad();
    }

    @AfterTest
    void closeBrowser() {
        // driver.close(); // closes 1 windows, process might be opened even if browser was closed.
        this.getDriver().quit(); // close browser opened by selenium, closes all windows and clears the memory
    }

    public WebDriver getDriver() {
        return BrowserController.getDriver();
    }

    //------------------------
    private int priority;
    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int _priority) {
        this.priority = _priority;
    }
}
