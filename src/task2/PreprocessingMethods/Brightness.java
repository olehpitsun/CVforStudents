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
public class Brightness implements PreProcessing {
    @Override
    public void getPreProcessingMethod() {
        List<Integer> brightnessValue =  Arrays.asList(1, 5, 10, 15, 20, 30 );
        for(int bv : brightnessValue){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\preprocessing\\brightness\\" + bv + ".jpg",
                    PreProcImgOperations.bright(Img.getImg(), bv));
        }
    }
}
