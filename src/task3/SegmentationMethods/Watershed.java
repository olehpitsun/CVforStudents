package task3.SegmentationMethods;

import entity.Img;
import org.opencv.highgui.Highgui;
import task3.interfaces.Segmentation;

/**
 * Created by oleh on 24.11.2016.
 */
public class Watershed implements Segmentation {
    @Override
    public void getSegmentationMethod() {

        Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\segmentation\\watershed\\" + 123 + ".jpg",
                tools.Segmentation.watershed(Img.getImg()));
    }
}
