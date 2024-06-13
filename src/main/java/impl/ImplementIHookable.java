package impl;

import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;

public class ImplementIHookable implements IHookable {
    @Override
    public void run(IHookCallBack iHookCallBack, ITestResult iTestResult) {
        System.out.println("Starting " + iTestResult.getName());
        iHookCallBack.runTestMethod(iTestResult);
        if (iTestResult.getThrowable() != null) {
            iTestResult.setThrowable(null);
            System.out.println("Second attempt " + iTestResult.getName());
            iHookCallBack.runTestMethod(iTestResult);
        }
    }
}
