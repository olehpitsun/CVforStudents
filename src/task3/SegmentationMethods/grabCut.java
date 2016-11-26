package task3.SegmentationMethods;

import entity.Img;
import org.opencv.highgui.Highgui;
import task3.interfaces.Segmentation;

/**
 * Created by oleh on 24.11.2016.
 */
public class grabCut implements Segmentation{

    @Override
    public void getSegmentationMethod() {
        Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\segmentation\\grabcut\\" + 123 + ".jpg",
                tools.Segmentation.grabCut(Img.getImg()));
    }
}
