import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchPage;
import steps.SearchResultsSteps;

public class TestSearchPagesLazyControlInit extends BaseTest {

    /**
     * @throws InterruptedException
     * @FindBy(xpath = "//input[@role='combobox']")
     * public WebElement searchField;
     * <p>
     * public SearchPage(){
     * super();
     * }
     * <p>
     * public void fillTheSearchField(String keyword){
     * searchField.click();
     * searchField.sendKeys(keyword);
     * }
     * s
     * BasePage
     * PageFactory.initElements(driver, this);
     */
    @Test
    public void usePageFactory() throws InterruptedException {
        SearchPage searchPage = new SearchPage();
        searchPage.fillTheSearchField("Selenium webdriver");
        searchPage.pressEnter();
        Thread.sleep(3000);

        SearchResultsSteps searchResultsSteps = new SearchResultsSteps();
        searchResultsSteps.verifyThatTopValueIsCorrect("https://www.browserstack.com › Guide");
    }
}
