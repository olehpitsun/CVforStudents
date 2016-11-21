package task2;

import task2.PreprocessingMethods.Brightness;
import task2.PreprocessingMethods.Contrast;
import task2.PreprocessingMethods.Dilate;
import task2.PreprocessingMethods.Erode;
import task2.interfaces.PreProcessing;

/**
 * Created by oleh on 19.11.2016.
 */

public class PreProcFactory {

    public PreProcessing getMethod(String method){

        switch (method){
            case "contrast":
                return new Contrast();
            case "brightness":
                return new Brightness();
            case "erode":
                return new Erode();
            case "dilate":
                return new Dilate();
            default:
                break;
        }
        return null;
    }
}
