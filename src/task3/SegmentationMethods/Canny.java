package task3.SegmentationMethods;

import entity.Img;
import org.opencv.highgui.Highgui;
import task3.interfaces.Segmentation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 21.11.2016.
 */
public class Canny implements Segmentation {
    @Override
    public void getSegmentationMethod() {
        List<Integer> thresholdValues =  Arrays.asList(2, 3, 4, 5, 7);
        for (int tv : thresholdValues){

            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\segmentation\\canny\\" + tv + ".jpg",
                    tools.Segmentation.cannyDetection(Img.getImg(), tv));
        }

    }
}
