package tests;

import base.BaseTest;
import impl.ImplementIHookable;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ImplementIHookable.class)
public class LearnIHookable extends BaseTest {
    private static int attempt = 1;

    @Test
    public void learnRetry() {
        if (attempt == 3) {
            attempt = 1;
        } else {
            Assert.fail("Failed on " + (attempt++) + " attempt");
        }
    }
}
