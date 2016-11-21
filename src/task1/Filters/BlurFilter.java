package task1.Filters;

import org.opencv.highgui.Highgui;
import entity.Img;
import task1.interfaces.Filter;
import tools.Filters;

import java.util.List;

/**
 * Created by oleh_pi on 18.11.2016.
 */
public class BlurFilter implements Filter {
    @Override
    public void getFilter(List<Integer> windowSize) {
        for(int wS : windowSize){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\res\\Blur\\" + wS + ".jpg",
                    Filters.Blur(Img.getImg(), wS));
        }
    }
}
