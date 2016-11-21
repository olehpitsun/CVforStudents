package task3;

import task3.SegmentationMethods.Canny;
import task3.SegmentationMethods.Kmeans;
import task3.SegmentationMethods.Laplacian;
import task3.SegmentationMethods.Thresholding;
import task3.interfaces.Segmentation;

/**
 * Created by oleh on 21.11.2016.
 */
public class SegmentationFactory {

    public Segmentation getMethod(String method){

        switch (method){
            case "canny":
                return new Canny();
            case "laplacian":
                return new Laplacian();
            case "kmeans":
                return new Kmeans();
            case "threshold":
                return new Thresholding();
            default:
                break;
        }
        return null;
    }
}
