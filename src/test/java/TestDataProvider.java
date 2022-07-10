import base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestDataProvider extends BaseTest {

    @Test(dataProvider = "dataProvider")
    public void searchByKeywordSeleniumHaveToFindSeleniumHqOrgInTop(String searchKeyWord) {
        steps.searchByKeyword(searchKeyWord)
                .verifyThatTopValueIsCorrect("https://www.selenium.dev › documentation")
                .verifyAllSearchResultsContainsExpectedValue(searchKeyWord);
    }

    @DataProvider(name = "dataProvider")
    public Object[][] dataProviderD() {
        return new Object[][]{{"selenium java"}};
    }
}
