package task3.SegmentationMethods;

import entity.Img;
import org.opencv.highgui.Highgui;
import task3.interfaces.Segmentation;
import tools.Cluster;
import tools.PreProcImgOperations;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 21.11.2016.
 */
public class Kmeans implements Segmentation {
    @Override
    public void getSegmentationMethod() {
        List<Integer> clusters =  Arrays.asList(1, 2, 3, 4, 5, 7 );
        for(int cluster : clusters){
            Highgui.imwrite("C:\\Projects\\CVforStudents\\img\\segmentation\\kmeans\\clusters_hsv_" + cluster + ".jpg",
                    Cluster.calculateClusters(Img.getImg(), cluster));
        }
    }
}
