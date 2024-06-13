package base;

public abstract class HelperBase {

    protected BrowserController browserController;

    public HelperBase(BrowserController manager) {
        this.browserController = manager;
    }
}