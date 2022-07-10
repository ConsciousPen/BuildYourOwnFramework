package steps;

import pages.SearchPage;

public class SearchSteps extends BaseSteps {
    private final SearchPage searchPage = new SearchPage();

    public SearchResultsSteps searchByKeyword(String keyword) {
        searchPage.fillTheSearchField(keyword);
        searchPage.trickyClickButton();
        return new SearchResultsSteps();
    }

    public SearchSteps openTooltip() {
        searchPage.moveToVoiceSearchButton();
        return this;
    }

    public SearchSteps verifyTooltipContainsProperText(String text) {
        searchPage.assertThatVoiceSearchTooltipContainsText(text);
        return this;
    }

}
