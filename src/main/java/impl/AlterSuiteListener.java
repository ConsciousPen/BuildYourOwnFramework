package impl;

import org.testng.IAlterSuiteListener;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.List;

public class AlterSuiteListener implements IAlterSuiteListener {

    @Override
    public void alter(List<XmlSuite> suites) {
        // Create a new suite for combining the suites of suites
        XmlSuite combinedSuite = new XmlSuite();
        combinedSuite.setName("Combined Suite");

        // Iterate over the suites of suites and combine them into the combined suite
        for (XmlSuite suite : suites) {
            // Add all the child suites to the combined suite
            combinedSuite.getChildSuites().addAll(suite.getChildSuites());

            // Add all the tests from each suite to the combined suite
            for (XmlTest test : suite.getTests()) {
                combinedSuite.addTest(test);
            }
        }

        // Replace the original suites with the combined suite
        suites.clear();
        suites.add(combinedSuite);
    }
}
