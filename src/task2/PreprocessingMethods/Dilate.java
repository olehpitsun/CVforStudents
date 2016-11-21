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

public class Dilate implements PreProcessing{
    @Override
    public void getPreProcessingMethod() {
        List<Integer> dilateValue =  Arrays.asList(1, 2, 5, 7, 10, 12 );
        for(int dv : dilateValue){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\preprocessing\\dilate\\" + dv + ".jpg",
                    PreProcImgOperations.Dilate(Img.getImg(), dv));
        }
    }
}
