package task3;

import task3.SegmentationMethods.*;
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
            case "watershed":
                return new Watershed();
            case "grabcut":
                return new grabCut();
            default:
                break;
        }
        return null;
    }
}
