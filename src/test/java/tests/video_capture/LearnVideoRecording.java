package tests.video_capture;

import base.BaseTest;
import base.BrowserController;
import config.LocalDesiredCapabilities;
import enums.TestProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import video.VideoCreator;

import java.io.File;
import java.lang.reflect.Method;

public class LearnVideoRecording extends BaseTest {

    private VideoCreator screencaster;
    private Thread videoThread;

    @BeforeMethod
    public void setUp(Method m) {
        File videoFile = new File(m.getName() + ".flv");
        screencaster = new VideoCreator(videoFile);
        videoThread = new Thread(new Runnable() {
            @Override
            public void run() {
                screencaster.createVideoFromScreen();
            }
        });
        videoThread.start();
    }

    @Test
    public void test1() {
        BrowserController.getDriver().get("http://www.seleniumhq.org/");
    }

    @Test
    public void test2() {
        BrowserController.getDriver().get("http://selenium2.ru/");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        screencaster.setPleaseStop(true);
        while (!screencaster.isStoppedCreation()) {
            Thread.sleep(500);
        }
        videoThread.join();
    }


}
