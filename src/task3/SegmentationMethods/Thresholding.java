package task3.SegmentationMethods;

import entity.Img;
import org.opencv.highgui.Highgui;
import task3.interfaces.Segmentation;

/**
 * Created by oleh_pi on 21.11.2016.
 */
public class Thresholding implements Segmentation {
    @Override
    public void getSegmentationMethod() {
        Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\segmentation\\threshold\\" + 123 + ".jpg",
                tools.Segmentation.thresholding(Img.getImg(), 50, 100));
    }
}
