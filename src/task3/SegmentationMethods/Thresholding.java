package task3.SegmentationMethods;

import entity.Img;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import task3.interfaces.Segmentation;
import tools.PreProcImgOperations;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 21.11.2016.
 */
public class Thresholding implements Segmentation {

    File f = null;

    @Override
    public void getSegmentationMethod() {


        Mat src= new Mat();
        Imgproc.cvtColor(Img.getImg(), src, Imgproc.COLOR_RGB2GRAY);

        List<String> threshType =  Arrays.asList(
                "THRESH_BINARY_INV"
                /*"THRESH_BINARY", "THRESH_OTSU", "THRESH_TOZERO","THRESH_TRUNC",
                "ADAPTIVE_THRESH_GAUSSIAN_C", "ADAPTIVE_THRESH_MEAN_C", "THRESH_BINARY_INV",
                "THRESH_BINARY+THRESH_OTSU"*/
                );

        List<Integer> thresholdValues =  Arrays.asList( -1,50,60,65,70, 75, 80, 85, 87, 90, 93, 95, 97, 100, 103, 105, 113, 115, 118, 120, 123,
                125, 127, 130, 135, 137, 140, 143, 145, 147, 150, 153,155);




        for(String tType : threshType) {
            try {

                f = new File("C:\\bioimg\\testsegmentation\\thresholding\\TS_06_19_13_17_42\\" + tType + "\\");
                f.mkdir();

                for (int threshV : thresholdValues) {
                    Mat dst = new Mat();
                    //Imgproc.threshold(src, dst, 43, 255, Imgproc.THRESH_BINARY);
                    Highgui.imwrite("C:\\bioimg\\testsegmentation\\thresholding\\TS_06_19_13_17_42\\" + tType +"\\"+ threshV + ".jpg",
                            PreProcImgOperations.Dilate(
                                    tools.Segmentation.thresholding(src, threshV, 255, "THRESH_BINARY_INV"),
                                    5
                            ));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
