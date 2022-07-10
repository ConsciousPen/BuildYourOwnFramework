import base.BaseTest;
import org.testng.annotations.Test;

public class TestSearchStepsApproach extends BaseTest {

    /**
     * public SearchResultsSteps searchByKeyword(String keyword) {
     * searchPage.fillTheSearchField(keyword);
     * searchPage.pressEnter();
     * return new SearchResultsSteps();
     * }
     * <p>
     * public SearchResultsSteps verifyThatTopValueIsCorrect(String expectedValue){
     * searchResultPage.assertThatExpectedValueIsOnSearchTop(expectedValue);
     * return this;
     * }
     * <p>
     * public SearchResultsSteps verifyAllSearchResultsContainsExpectedValue(String expectedValue){
     * searchResultPage.assertAllSearchResultsContainsExpectedValue(expectedValue);
     * return this;
     * }
     */
    private static final String searchKeyWord = "Selenium dev";

    @Test
    public void searchByKeywordSeleniumHaveToFindSeleniumHqOrgInTop() {
        steps.searchByKeyword(searchKeyWord)
                .verifyThatTopValueIsCorrect("https://www.selenium.dev")
                .verifyAllSearchResultsContainsExpectedValue(searchKeyWord);
    }

    @Test
    public void verifyToolkitTest() {
        steps.openTooltip().verifyTooltipContainsProperText("Search by voice");
    }

}
