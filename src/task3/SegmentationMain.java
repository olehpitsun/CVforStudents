package task3;

import entity.Img;
import org.opencv.core.Core;
import org.opencv.highgui.Highgui;
import task3.interfaces.Segmentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh on 21.11.2016.
 */
public class SegmentationMain {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //вхідне зображення
        String imagePath = "C:\\Projects\\CVforStudents\\img\\30.jpg";

        Img.setImg(Highgui.imread(imagePath, Highgui.CV_LOAD_IMAGE_COLOR));

        //preProcItems - список методів сегментації
        List<String>segmentationItems = new ArrayList<>();
        //segmentationItems.add("canny");
        //segmentationItems.add("laplacian");
        segmentationItems.add("kmeans");
        //segmentationItems.add("threshold");


        SegmentationFactory segmentationFactory = new SegmentationFactory();
        for(String items : segmentationItems){
            Segmentation segmentation = segmentationFactory.getMethod(items);
            segmentation.getSegmentationMethod();
        }

    }
}
