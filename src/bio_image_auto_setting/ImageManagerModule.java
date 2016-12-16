package bio_image_auto_setting;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import tools.Filter.FiltersOperations;
import tools.PreProcImgOperations;
import tools.Segmentation;

import java.util.ArrayList;
import java.util.List;

import static tools.HistogramEq.HE.Histogram;


/**
 * Created by oleh on 11.08.2016.
 */
public class ImageManagerModule {

    public Mat autoImageCorrection(Mat src){

        FiltersOperations filtroperation = new FiltersOperations(src, "4", "5", "", "", ""); // медіанний фільтр

        Mat brightMat = PreProcImgOperations.bright(filtroperation.getOutputImage(), 10); // яскравість

        //Mat histogramMat = histogrmEqualization(brightMat);

        Mat result = Segmentation.watershed(brightMat);

        return result;
    }


}