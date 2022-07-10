import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestSearch extends BaseTest {
    By searchField = By.xpath("//input[@role='combobox']");

    @Test
    public void openGoogleCom() {
        getDriver().findElement(searchField).clear();
        getDriver().findElement(searchField).sendKeys("Selenium webdriver");
        getDriver().findElement(searchField).sendKeys(Keys.RETURN);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement resultRow = getDriver().findElement(By.xpath("//div[@class='g']"));
        System.out.println(resultRow.getText());
        System.out.println(resultRow.getDomAttribute("class"));
        System.out.println(getDriver().getTitle());
        assertTrue(resultRow.isDisplayed(), "Element has not been displayed!");
        assertThat(resultRow.getText().toLowerCase()).contains("selenium", "webdriver");
    }

    @Test
    public void imFeelingLucky() {
        WebElement feelingLucky = getDriver().findElement(By.name("btnI"));
        assertTrue(feelingLucky.isEnabled());
        assertEquals(feelingLucky.getAttribute("value"), "I'm Feeling Lucky", "Wrong text has been deisplayed");

    }

}
