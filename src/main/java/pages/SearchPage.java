package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchPage extends BasePage {

    // Steps and classic approach
    private final By inputSearchField = By.xpath("//input[@role='combobox']");
    private final By buttonGoogleSearch = By.xpath("//input[@type='submit']");
    private final By pageBody = By.xpath("//body");
    private final By buttonSearchVoice = By.xpath("//div[@aria-label='Search by voice'][@role='button']");
    private final String tooltipTemplate = "//*[contains(text(),'%s')]";

    // Lazy init approach/
    //@FindBy(xpath = "//input[@role='combobox']") public WebElement searchField;

    public SearchPage() {
        super();
    }

    public void fillTheSearchField(String keyword) {
        WebElement searchField = findElement(inputSearchField);
        pasteTextToTheElementFromTheClipboard(keyword);
        searchField.click();
        searchField.clear();
        // searchField.sendKeys(keyword);
        Actions actions = new Actions(driver);
        actions.moveToElement(searchField);
        actions.click(searchField);
        actions.keyDown(Keys.META);
        actions.sendKeys("v");
        actions.keyUp(Keys.META);
        actions.perform();

    }

    public void pressEnter() {
        WebElement searchField = findElement(inputSearchField);
        searchField.sendKeys(Keys.RETURN);
    }

    public void trickyClickButton() {
        if (isElementPresent("btnK", 3)) {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(inputSearchField));
            findElement(inputSearchField).click();
        } else {
            pressEnter();
        }
    }

    /**
     * JS
     */
    public void fillTheSearchFieldJS(String keyword) {
        WebElement searchField = findElement(inputSearchField);
        clickJavaScript(searchField);
        sendKeysJavaScript(searchField, keyword);
        clickJavaScript(driver.findElement(buttonGoogleSearch));
    }

    public void moveToVoiceSearchButton() {
        builder.moveToElement(driver.findElement(buttonSearchVoice)).build().perform();
    }

    public void assertThatVoiceSearchTooltipContainsText(String text) {
        assertThat(driver.findElement(pageBody).findElements(By.xpath(String.format(tooltipTemplate, text))).size())
                .isGreaterThan(0);

    }

}
