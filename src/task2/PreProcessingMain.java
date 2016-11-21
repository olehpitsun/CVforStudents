package task2;

import entity.Img;
import org.opencv.core.Core;
import org.opencv.highgui.Highgui;
import task2.interfaces.PreProcessing;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh on 19.11.2016.
 */
public class PreProcessingMain {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //вхідне зображення
        String imagePath = "C:\\Projects\\CVforStudents\\img\\1.jpg";

        Img.setImg(Highgui.imread(imagePath, Highgui.CV_LOAD_IMAGE_COLOR));

        //preProcItems - список методів попередньої обробки зображень
        List<String> preProcItems = new ArrayList<>();
        preProcItems.add("contrast");
        preProcItems.add("brightness");
        preProcItems.add("erode");
        preProcItems.add("dilate");
        /*preProcItems.add("HE");*/

        PreProcFactory preProcFactory = new PreProcFactory();
        for(String items : preProcItems){
            PreProcessing preProcessing = preProcFactory.getMethod(items);
            preProcessing.getPreProcessingMethod();
        }

    }
}
