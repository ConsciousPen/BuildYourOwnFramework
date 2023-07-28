package impl;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SuiteListener implements ISuiteListener {

    private List<XmlSuite> allSuites = new ArrayList<>();

    @Override
    public void onStart(ISuite suite) {
        allSuites.addAll(suite.getXmlSuite().getChildSuites());
        System.out.println("Suite: " + suite.getName() + " is starting.");
        XmlSuite combinedSuite = null;
        if (allSuites.isEmpty()) {
            combinedSuite = suite.getXmlSuite();
        } else {
            combinedSuite = combineSuites(allSuites);
        }

        printPrettyXmlSuite2(combinedSuite);
    }
    private XmlSuite combineSuites(List<XmlSuite> suites) {
        XmlSuite combinedSuite = new XmlSuite();
        combinedSuite.setName("Combined Suite");

        List<String> combinedListeners = new ArrayList<>(); // Store the combined listeners

        for (XmlSuite suite : suites) {
            combinedSuite.getTests().addAll(suite.getTests());

            // Add the listeners from each suite to the combinedListeners list
            List<String> newSuiteListeners = suite.getListeners();
            combinedListeners.addAll(newSuiteListeners.stream()
                    .filter(listener -> !combinedListeners.contains(listener))
                    .collect(Collectors.toList()));
        }

        // Set the combined listeners to the combined suite
        combinedSuite.setListeners(combinedListeners);

        return combinedSuite;
    }
    @Override
    public void  onFinish(ISuite suite) {
        System.out.println("Suite: " + suite.getName() + " is finished.");
    }

    private void printPrettyXmlSuite2(XmlSuite xmlSuite) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            // Create the suite element
            Element suiteElement = document.createElement("suite");
            suiteElement.setAttribute("name", xmlSuite.getName());
            suiteElement.setAttribute("parallel", xmlSuite.getParallel().toString());
            suiteElement.setAttribute("thread-count", String.valueOf(xmlSuite.getThreadCount()));
            document.appendChild(suiteElement);

            // Create the listeners element
            Element listenersElement = document.createElement("listeners");
            suiteElement.appendChild(listenersElement);

            // Get the listener from the suite and create the listener element
            List<String> suiteListeners = xmlSuite.getListeners();
            for (String listener : suiteListeners) {
                Element listenerElement = document.createElement("listener");
                listenerElement.setAttribute("class-name", listener);
                listenersElement.appendChild(listenerElement);
            }


            // Create the test elements
            List<XmlTest> xmlTests = xmlSuite.getTests();
            for (XmlTest xmlTest : xmlTests) {
                Element testElement = document.createElement("test");
                testElement.setAttribute("name", xmlTest.getName());
                suiteElement.appendChild(testElement);

                // Retrieve the parameters from the <test> element itself
                Map<String, String> parameters = xmlTest.getLocalParameters();
                for (Map.Entry<String, String> entry : parameters.entrySet()) {
                    Element parameterElement = document.createElement("parameter");
                    parameterElement.setAttribute("name", entry.getKey());
                    parameterElement.setAttribute("value", entry.getValue());
                    testElement.appendChild(parameterElement);
                }

                // Create the classes element
                Element classesElement = document.createElement("classes");
                testElement.appendChild(classesElement);

                // Create the class elements
                List<XmlClass> xmlClasses = xmlTest.getClasses();
                for (XmlClass xmlClass : xmlClasses) {
                    Element classElement = document.createElement("class");
                    classElement.setAttribute("name", xmlClass.getName());
                    classesElement.appendChild(classElement);

                    // Create the methods element
                    Element methodsElement = document.createElement("methods");
                    classElement.appendChild(methodsElement);

                    // Create the include elements for each method
                    List<XmlInclude> includedMethods = xmlClass.getIncludedMethods();
                    for (XmlInclude include : includedMethods) {
                        Element includeElement = document.createElement("include");
                        includeElement.setAttribute("name", include.getName());
                        methodsElement.appendChild(includeElement);
                    }
                }
            }

            // Transform the document to XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            StringWriter stringWriter = new StringWriter();
            StreamResult streamResult = new StreamResult(stringWriter);
            DOMSource domSource = new DOMSource(document);
            transformer.transform(domSource, streamResult);
            String formattedXml = stringWriter.toString();
            System.out.println(formattedXml);
        } catch (ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private Element createTestElement(Document document, XmlTest xmlTest) {
        Element testElement = document.createElement("test");
        testElement.setAttribute("name", xmlTest.getName());

        // Iterate over the classes in the test
        List<XmlClass> xmlClasses = xmlTest.getClasses();
        for (XmlClass xmlClass : xmlClasses) {
            Element classElement = document.createElement("class");
            classElement.setAttribute("name", xmlClass.getName());
            testElement.appendChild(classElement);
        }

        return testElement;
    }
    private Element createSuiteElement(Document document, XmlSuite xmlSuite) {
        Element suiteElement = document.createElement("suite");
        suiteElement.setAttribute("name", xmlSuite.getName());
        suiteElement.setAttribute("parallel", xmlSuite.getParallel().toString());
        suiteElement.setAttribute("thread-count", String.valueOf(xmlSuite.getThreadCount()));
        Map<String, String> suiteParameters = xmlSuite.getParameters();
        if (!suiteParameters.isEmpty()) {
            Element parametersElement = document.createElement("parameters");
            suiteElement.appendChild(parametersElement);
            for (Map.Entry<String, String> entry : suiteParameters.entrySet()) {
                Element parameterElement = document.createElement("parameter");
                parameterElement.setAttribute("name", entry.getKey());
                parameterElement.setAttribute("value", entry.getValue());
                parametersElement.appendChild(parameterElement);
            }
        }
        List<XmlTest> xmlTests = xmlSuite.getTests();
        for (XmlTest xmlTest : xmlTests) {
            List<XmlClass> xmlClasses = xmlTest.getClasses();
            for (XmlClass xmlClass : xmlClasses) {
                Element classElement = document.createElement("class");
                classElement.setAttribute("name", xmlClass.getName());
                suiteElement.appendChild(classElement);
            }
        }
        return suiteElement;
    }
}

