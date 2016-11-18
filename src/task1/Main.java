package task1;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import task1.entity.Img;
import task1.interfaces.Filter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 18.11.2016.
 */
public class Main {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //хідне зображення
        String imagePath = "C:\\Projects\\CVforStudents\\img\\1.jpg";

        Img.setImg(Highgui.imread(imagePath, Highgui.CV_LOAD_IMAGE_COLOR));

        //filterItems - список фільрів
        List<String> filterItems = new ArrayList<>();
        filterItems.add("Bilateral");
        filterItems.add("AdaptiveBilateral");
        filterItems.add("Blur");
        filterItems.add("Gaussian");
        filterItems.add("Median");

        // розмін вікон фільтрів
        List<Integer> windowSize =  Arrays.asList(3, 5, 7, 9, 13, 17, 19, 25);

        FilterFactory filterFactory = new FilterFactory();
        for(String item : filterItems){
            Filter filter = filterFactory.getFilter(item);
            filter.getFilter(windowSize);
        }
    }
}
