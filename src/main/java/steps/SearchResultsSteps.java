package steps;

import pages.SearchResultPage;

public class SearchResultsSteps extends BaseSteps {
    private SearchResultPage searchResultPage = new SearchResultPage();

    public SearchResultsSteps verifyThatTopValueIsCorrect(String expectedValue) {
        searchResultPage.assertThatExpectedValueIsOnSearchTop(expectedValue);
        return this;
    }

    public SearchResultsSteps verifyAllSearchResultsContainsExpectedValue(String expectedValue) {
        searchResultPage.assertAllSearchResultsContainsExpectedValue(expectedValue);
        return this;
    }
}
