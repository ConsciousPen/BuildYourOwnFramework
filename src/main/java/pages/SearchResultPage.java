package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class SearchResultPage extends BasePage {

    // Steps and classic approach
    private final By resultRows = By.xpath("//cite[contains(@class,'iUh30')]");
    // Lazy init approach/
    //  @FindBy(xpath = "//cite[contains(@class,'iUh30')]")
    // List<WebElement> resultRows;
    // private final By resultRow = By.xpath("//div[@class='g']//h3");
    //  @FindBy(xpath = "//div[@class='g']//h3")
    // WebElement resultRow;

    public SearchResultPage() {
        super();
    }

    public void assertThatExpectedValueIsOnSearchTop(String expectedValue) {
        assertEquals(findElements(resultRows).get(0).getText(), expectedValue, expectedValue + " is not the first result!");
        List<String> collect = findElements(resultRows).stream().map(el -> el.getText()).collect(Collectors.toList());
        assertThat(collect).contains(expectedValue);
        // Lazy init approach assertEquals(resultRows.get(0).getText(), expectedValue, expectedValue + " is not the first result!");

    }

    public void assertAllSearchResultsContainsExpectedValue(String expectedValue) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(resultRows));
        findElements(resultRows).stream().map(c -> assertThat(c.getText()).contains(expectedValue));
        // Lazy init approach  resultRows.stream().map(c -> assertThat(c.getText()).contains(expectedValue));
    }

    public void assertSearchResultsContainsExpectedClassAttribute(String expectedValue) {
        // assertThat(findElement(resultRow).getAttribute("class")).as("Wrong class attribute")..isEqualTo(expectedValue);
    }

    public void assertAllSearchResultsContainsExpectedValueJS(String expectedValue) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(resultRows));
        findElements(resultRows).stream().map(c -> assertThat(getTextJs(c)).contains(expectedValue));
        // Lazy init approach  resultRows.stream().map(c -> assertThat(c.getText()).contains(expectedValue));
    }


}
