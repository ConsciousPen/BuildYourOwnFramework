package misc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

public class Jackson {
    public static void main(String args[]) throws IOException {
        CustomerDetails customerDetails = new CustomerDetails().setAge(11).setLastname("last Name").setName("first name");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("jackson"), customerDetails);
        objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerDetails);

        ObjectMapper reader = new ObjectMapper();
        CustomerDetails customerDetails1 = reader.readValue(new File("jackson"), CustomerDetails.class);

        Process process = Runtime.getRuntime().exec(new String[]{"cmd /c start startDocker.bat"});

        boolean flag = false;
        String fileName = "logs.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String currentLine = br.readLine();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 30);
        long stopNowTime = calendar.getTimeInMillis();

        while (System.currentTimeMillis() < stopNowTime) {
            while (currentLine != null) {
                if (currentLine.contains("the hub and ready to use")) {
                    flag = true;
                    break;
                }
                currentLine = br.readLine();
            }

        }
        br.close();
        Assert.assertTrue(flag);

    }

    @Test
    public void test() {
        try {
            ChromeOptions options = new ChromeOptions();
            URL url = new URL("http://localhost:4444");
            RemoteWebDriver driver = new RemoteWebDriver(url, options);

            driver.get("https://google.com");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }
}
