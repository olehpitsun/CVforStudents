package task3.SegmentationMethods;

import entity.Img;
import org.opencv.highgui.Highgui;
import task3.interfaces.Segmentation;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 21.11.2016.
 */
public class Laplacian implements Segmentation {
    @Override
    public void getSegmentationMethod() {
        List<Integer> thresholdValues =  Arrays.asList(1, 3, 5, 13, 15, 17, 25 );
        for (int tv : thresholdValues){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\segmentation\\laplacian\\" + tv + ".jpg",
                    tools.Segmentation.Laplacian(Img.getImg(), tv, 9));
        }
    }
}
