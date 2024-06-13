package video;

import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

public class VideoCreator {

    private static final long FRAME_RATE = 25;
    private static final Dimension SCREEN_BOUNDS = Toolkit.getDefaultToolkit().getScreenSize();

    private File outputFile;
    private AtomicBoolean pleaseStop = new AtomicBoolean(false);
    private boolean stoppedCreation = true;

    public VideoCreator() {

    }

    public VideoCreator(File outputFile) {
        this.outputFile = outputFile;
    }

    public static BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
        BufferedImage image = null;

        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        } else {
            image = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;
    }

    public void createVideoFromScreen() {
        FFmpegFrameRecorder recorder = null;
        try {
            if (getOutputFile() == null) {
                throw new IllegalStateException("Output video file cannot be null");
            }

            setStoppedCreation(false);

            recorder = new FFmpegFrameRecorder(getOutputFile().getAbsolutePath(), SCREEN_BOUNDS.width, SCREEN_BOUNDS.height);
            recorder.setFrameRate(FRAME_RATE);
            recorder.setVideoCodec(org.bytedeco.ffmpeg.global.avcodec.AV_CODEC_ID_H264);
            recorder.setFormat("mp4");
            recorder.start();

            Java2DFrameConverter converter = new Java2DFrameConverter();
            long startTime = System.nanoTime();

            while (!getPleaseStop()) {
                BufferedImage screen = getDesktopScreenshot();
                BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);
                Frame frame = converter.convert(bgrScreen);
                recorder.record(frame);

                try {
                    Thread.sleep(1000 / FRAME_RATE);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            setStoppedCreation(true);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (recorder != null) {
                try {
                    recorder.stop();
                    recorder.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private BufferedImage getDesktopScreenshot() {
        try {
            Robot robot = new Robot();
            Rectangle captureSize = new Rectangle(SCREEN_BOUNDS);
            return robot.createScreenCapture(captureSize);
        } catch (AWTException e) {
            throw new RuntimeException("Error occurred while getting desktop screenshot", e);
        }
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public boolean getPleaseStop() {
        return pleaseStop.get();
    }

    public void setPleaseStop(boolean pleaseStop) {
        this.pleaseStop.set(pleaseStop);
    }

    public boolean isStoppedCreation() {
        return stoppedCreation;
    }

    private void setStoppedCreation(boolean stoppedCreation) {
        this.stoppedCreation = stoppedCreation;
    }
}
