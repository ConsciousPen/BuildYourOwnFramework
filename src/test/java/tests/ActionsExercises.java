package tests;

import base.BaseTest;
import enums.TestProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.LogManager;

public class ActionsExercises extends BaseTest {

    @Test
    public void threads() throws InterruptedException {
        getDriver().get("https://www.ksrtc.in");
        // ScreenShotManager.saveScreenShot();
    }

    @Test
    public void logging() {
       // logger.info("logging info test");
       // logger.warn("logging warn test");
       // logger.debug("logging debug test");
       // logger.error("logging error test", new NullPointerException("NullError"));

    }

    @Test
    public void mouseOver() {
        Actions actions = new Actions(getDriver());
        By accountList = By.id("nav-link-accountList");
        By searchField = By.id("twotabsearchtextbox");
        WebElement element = getDriver().findElement(accountList);
        actions.moveToElement(element).perform();
        actions.moveToElement(getDriver().findElement(searchField)).doubleClick().keyDown(Keys.SHIFT).sendKeys(Keys.SHIFT, "test").perform();
        actions.moveToElement(element).perform();
    }

    @Test
    public void dragAndDrop() throws MalformedURLException {
        URL url = new URL("http://jqueryui.com/droppable/");
        Actions actions = new Actions(getDriver());

        getDriver().get(url.toString());

        getDriver().switchTo().frame(getDriver().findElement(By.className("demo-frame")));
        WebElement droppable = getDriver().findElement(By.id("droppable"));
        WebElement draggable = getDriver().findElement(By.id("draggable"));

        actions.dragAndDrop(draggable, droppable).perform();

        getDriver().switchTo().defaultContent();
    }

    @Test
    public void tabs()  {
        System.setProperty("webdriver.chrome.driver", TestProperties.CHROME_DRIVER);

        getDriver().get("http://qaclickacademy.com/practice.php");
        System.out.println(getDriver().findElements(By.tagName("a")).size());

        WebElement footer = getDriver().findElement(By.id("gf-BIG"));// Limiting webdriver scope

        System.out.println(footer.findElements(By.tagName("a")).size());

        //3-
        WebElement column = footer.findElement(By.xpath("//table/tbody/tr/td[1]/ul"));
        System.out.println(column.findElements(By.tagName("a")).size());

        //4- click on each link in the column and check if the pages are opening-
        for (int i = 1; i < column.findElements(By.tagName("a")).size(); i++) {
            String clickOnLinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);

            column.findElements(By.tagName("a")).get(i).sendKeys(clickOnLinkTab);
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }// opens all the tabs
        Set<String> abc = getDriver().getWindowHandles();//4
        Iterator<String> it = abc.iterator();

        while (it.hasNext()) {
            getDriver().switchTo().window(it.next());
            System.out.println(getDriver().getTitle());
        }
    }
    By inputLeavingFrom = By.xpath("//input[@id='fromPlaceName']");

    @Test
    public void dropdown() throws InterruptedException {
        String bengaluru_internation_aiport = "BENGALURU INTERNATION AIRPORT";

        getDriver().get("https://www.ksrtc.in");
        getDriver().findElement(inputLeavingFrom).sendKeys(bengaluru_internation_aiport.substring(0, 4));
        Thread.sleep(2000);

        getDriver().findElement(inputLeavingFrom).sendKeys(Keys.DOWN);

        System.out.println(getDriver().findElement(inputLeavingFrom).getText());

        autoSuggestiveField(bengaluru_internation_aiport);
    }

    @Test
    public void takeScreenShot() throws InterruptedException {
        getDriver().get("https://www.ksrtc.in");
        //ScreenShotManager.saveScreenShot();
    }

    private void autoSuggestiveField(String value) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();

        String script = "return document.getElementById(\"fromPlaceName\").value;";

        long millis = Long.parseLong("5000") + System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();

        while (!js.executeScript(script).toString().equalsIgnoreCase(value)
                && currentTime < millis) {

            getDriver().findElement(inputLeavingFrom).sendKeys(Keys.DOWN);
            currentTime = System.currentTimeMillis();
            System.out.println(System.currentTimeMillis());
        }
    }

}
