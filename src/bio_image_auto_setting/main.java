package bio_image_auto_setting;

import comparator.Main;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import tools.ImageSettings;
import tools.StartImageParams;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 16.12.2016.
 */
public class main {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        /**
         * ОРИГІНАЛЬНЕ ЗОБРАЖЕННЯ
         */
        String imagePath = "C:\\bioimg\\testsegmentation\\watershed\\auto\\\\TS_06_19_13_17_42\\\\TS_06_19_13_17_42.bmp";
        Mat originalMat = Highgui.imread(imagePath); // зчитування зображення
        StartImageParams.getStartValues(originalMat); // початкові дані зображення

        /**
         * ЕКСПЕРТНЕ ЗОБРАЖЕННІ
         */
        String expertImgPath = "C:\\bioimg\\testsegmentation\\watershed\\auto\\\\TS_06_19_13_17_42\\expert.png";
        Mat expertImgMat = Highgui.imread(expertImgPath); // зчитування зображення

        /**
         * ПІДІГНАТИ РОЗМІРИ ЗОБРАЖЕНЬ
         */

        ImageSettings imageSettings = new ImageSettings(originalMat, expertImgMat);
        Mat normalSegmentedImgMat = imageSettings.getResizedImageMat();

        Imgproc.cvtColor(expertImgMat, expertImgMat, Imgproc.COLOR_RGB2GRAY); // перетворення зобр в градації сірого

        List<Integer> lowTreshValue = Arrays.asList(75,80,85,90,95);
        for(int i = 0; i < lowTreshValue.size(); i++)
        {
            Mat newImageMat = new Mat();
            normalSegmentedImgMat.copyTo(newImageMat);

            /**
             * ЗОБРАЖЕННЯ ПІСЛЯ ОБРОБКИ
             */
            ImageManagerModule imageManagerModule = new ImageManagerModule();
            newImageMat = imageManagerModule.autoImageCorrection(newImageMat,lowTreshValue.get(i));

            /**
             * ПОРОГОВА СЕШМЕНТАЦІЯ
             * перетворення експертного зобр в градації сірого
             */
            Imgproc.threshold(newImageMat, newImageMat, 200, 255, Imgproc.THRESH_BINARY);
            Highgui.imwrite("C:\\bioimg\\testsegmentation\\watershed\\auto\\\\TS_06_19_13_17_42\\thresh_" + lowTreshValue.get(i) + ".jpg", newImageMat);

            /**
             * ПОРІВНЯННЯ ЗОБРАЖЕНЬ
             */
            System.out.println("\n====================\n lowTreshValue = " + lowTreshValue.get(i) );
            Main.main(newImageMat, expertImgMat);
        }
    }
}
