package task2.PreprocessingMethods;

import entity.Img;
import org.opencv.highgui.Highgui;
import task2.interfaces.PreProcessing;
import tools.PreProcImgOperations;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 19.11.2016.
 */
public class Erode implements PreProcessing {
    @Override
    public void getPreProcessingMethod() {
        List<Integer> erodeValue =  Arrays.asList(1, 2, 5, 7, 10, 12 );
        for(int ev : erodeValue){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\preprocessing\\erode\\" + ev + ".jpg",
                    PreProcImgOperations.Erode(Img.getImg(), ev));
        }
    }
}
