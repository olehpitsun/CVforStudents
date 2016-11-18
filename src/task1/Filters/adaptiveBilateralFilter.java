package task1.Filters;

import org.opencv.highgui.Highgui;
import task1.entity.Img;
import task1.interfaces.Filter;
import tools.Filters;

import java.util.List;

/**
 * Created by oleh on 18.11.2016.
 */
public class adaptiveBilateralFilter implements Filter {
    @Override
    public void getFilter(List<Integer> windowSize) {
        for(int wS : windowSize){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\res\\AdaptiveBilateral\\" + wS +".jpg",
                    Filters.adaptiveBilateralFilter(Img.getImg(), wS,3));
        }
    }
}
