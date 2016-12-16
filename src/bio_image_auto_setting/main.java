package bio_image_auto_setting;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import tools.StartImageParams;

/**
 * Created by oleh_pi on 16.12.2016.
 */
public class main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        String imagePath = "C:\\bioimg\\testsegmentation\\watershed\\auto\\TS_06_19_13_17_42\\TS_06_19_13_17_42.bmp";
        Mat originalMat = Highgui.imread(imagePath); // зчитування зображення
        StartImageParams.getStartValues(originalMat); // очаткові дані зображення

        ImageManagerModule imageManagerModule = new ImageManagerModule();
        Highgui.imwrite("C:\\bioimg\\testsegmentation\\watershed\\auto\\TS_06_19_13_17_42\\result.jpg",
                imageManagerModule.autoImageCorrection(originalMat));
    }
}
