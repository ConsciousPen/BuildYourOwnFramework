import base.DriverFactory;

public abstract class HelperBase {

    protected DriverFactory manager;

    public HelperBase(DriverFactory manager) {
        this.manager = manager;
    }
}