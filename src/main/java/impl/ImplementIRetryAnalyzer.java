package impl;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class ImplementIRetryAnalyzer implements IRetryAnalyzer {
    @Override
    public boolean retry(ITestResult iTestResult) {
        return !iTestResult.isSuccess();
    }
}
