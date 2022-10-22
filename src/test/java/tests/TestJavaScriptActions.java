package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchPage;
import pages.SearchResultPage;

public class TestJavaScriptActions extends BaseTest {
    @Test
    public void openGoogleCom() {
        SearchPage searchPage = new SearchPage();
        searchPage.fillTheSearchFieldJS("https://www.selenium.dev");


        SearchResultPage searchResultsSteps = new SearchResultPage();
        searchResultsSteps.assertAllSearchResultsContainsExpectedValueJS("selenium dev");
    }
}
