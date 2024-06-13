package pages;

import base.BrowserController;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;
import java.util.List;

//public class BasePage extends BaseTest{
public class BasePage {

    public WebDriver driver;
    public WebDriverWait webDriverWait;
    Actions builder;
    JavascriptExecutor executor;

    public BasePage() {
        this.driver = BrowserController.threadLocalDriver.get();
        this.webDriverWait = BrowserController.getWebDriverWait();
        this.builder = new Actions(driver);
        this.executor = (JavascriptExecutor) driver;
        // Init elements only at the moment of calling them (Lazy init)
        // PageFactory.initElements(driver, this);
        // PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 20), this);
    }

    public WebElement findElement(By by) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(by));
        WebElement elem = this.driver.findElement(by);
        // draw a border around the found element
        if (this.driver instanceof JavascriptExecutor) {
            ((JavascriptExecutor) this.driver).executeScript("arguments[0].style.border='3px solid red'", elem);
        }
        return elem;
    }

    public WebElement findElement(String stringXpath) {
        return findElement(By.xpath(stringXpath));
    }

    public List<WebElement> findElements(By stringXpath) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(stringXpath));
        return driver.findElements(stringXpath);
    }

    public boolean isElementPresent(String stringXpath, int timeout) {
        List<WebElement> elements = driver.findElements(By.xpath(stringXpath));
        for (int i = 0; (i < timeout) && (elements.size() == 0); i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            elements = driver.findElements(By.xpath(stringXpath));
        }
        return elements.size() > 0;
    }

    // ActionsExamples
    public void moveCursorAndDoubleClick(WebElement element) {
        moveCursorToTheElement(element);
        Actions builder = new Actions(driver);
        builder.doubleClick().build().perform();
    }

    public void moveCursorToTheElement(WebElement element) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
    }

    // Simulate CTRL + V
    // Typing letters is slow.
    // restrict availability of the methods which works with elements from being used in the tests body
    void pasteTextToTheElementFromTheClipboard(String text) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Clipboard clipboard = toolkit.getSystemClipboard();
        StringSelection stringSelection = new StringSelection(text);
        clipboard.setContents(stringSelection, null);

        // element.sendKeys(Keys.chord(Keys.COMMAND, "v"));
    }

    public void clickJavaScript(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", element);
    }

    public void sendKeysJavaScript(WebElement element, String text) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].value = '" + text + "'", element);
    }

    public String getTextJs(WebElement element) {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        return (String) jse.executeScript("return arguments[0].text", element);
    }

}
