package task3.SegmentationMethods;

import entity.Img;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import task3.interfaces.Segmentation;

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

        List<String> threshType =  Arrays.asList(//"THRESH_BINARY", "THRESH_OTSU", "THRESH_TOZERO","THRESH_TRUNC",
                //"ADAPTIVE_THRESH_GAUSSIAN_C", "ADAPTIVE_THRESH_MEAN_C", "THRESH_BINARY_INV"
                "THRESH_BINARY+THRESH_OTSU");

        List<Integer> thresholdValues =  Arrays.asList( -1,5, 15, 25, 35, 45, 55, 65, 75, 85, 95, 105, 115, 125, 135,
                145, 155, 165, 175, 185, 195, 205, 215, 225, 235, 245);




        for(String tType : threshType) {
            try {

                f = new File("C:\\Projects\\CVforStudents\\img\\segmentation\\threshold\\" + tType + "\\");
                f.mkdir();

                for (int threshV : thresholdValues) {
                    Mat dst = new Mat();
                    //Imgproc.threshold(src, dst, 43, 255, Imgproc.THRESH_BINARY);
                    Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\segmentation\\threshold\\" + tType +"\\"+ threshV + ".jpg",
                            tools.Segmentation.thresholding(src, threshV, 255, "THRESH_BINARY"));
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
}
