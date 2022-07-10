import base.BaseTest;
import org.testng.annotations.Test;
import pages.SearchPage;
import steps.SearchResultsSteps;

import java.util.concurrent.TimeUnit;

public class TestWaits extends BaseTest {

    @Test
    public void openGoogleCom() {
        getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        SearchPage searchPage = new SearchPage();
        searchPage.fillTheSearchField("test"); // waiter in find element
        searchPage.pressEnter();


        SearchResultsSteps searchResultsSteps = new SearchResultsSteps();
        searchResultsSteps.verifyThatTopValueIsCorrect("test");
    }
}
