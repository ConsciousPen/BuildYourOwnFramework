package video;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import java.io.File;

public class VideoListener implements IInvokedMethodListener {

    private VideoCreator videoCreator;
    private Thread videoThread;

    @Override
    public void beforeInvocation(IInvokedMethod m, ITestResult res) {
        if (m.isTestMethod()) {
            File videoFile = new File(
                    res.getTestContext().getOutputDirectory(),
                    m.getTestMethod().getMethodName() + ".flv");
            videoCreator = new VideoCreator(videoFile);
            videoThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    videoCreator.createVideoFromScreen();
                }
            });
            videoThread.start();
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod m, ITestResult res) {
        if (m.isTestMethod() && videoCreator != null) {
            try {
                Thread.sleep(2000);
                videoCreator.setPleaseStop(true);
                while (!videoCreator.isStoppedCreation()) {
                    Thread.sleep(500);
                }
                videoThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            videoCreator = null;
            videoThread = null;
        }
    }
}
