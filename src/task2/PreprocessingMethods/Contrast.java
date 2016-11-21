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
public class Contrast implements PreProcessing {
    @Override
    public void getPreProcessingMethod() {

        List<Double> contrsatValue =  Arrays.asList(0.5, 0.7, 0.9, 1.1, 1.3, 1.7 );
        for(Double cv : contrsatValue){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\preprocessing\\contrast\\" + cv + ".jpg",
                    PreProcImgOperations.contrast(Img.getImg(), cv));
        }
    }
}
