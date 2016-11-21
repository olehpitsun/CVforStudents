package tools;


import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh on 02.01.16.
 */
public class Segmentation {

    public static Mat cannyDetection(Mat image, int size){

        Mat grayImage = new Mat();
        Mat detectedEdges = new Mat();

        // convert to grayscale
        Imgproc.cvtColor(image, grayImage, Imgproc.COLOR_BGR2GRAY);

        // reduce noise with a 3x3 kernel
        Imgproc.blur(grayImage, detectedEdges, new Size(3, 3));

        // canny detector, with ratio of lower:upper threshold of 3:1
        Imgproc.Canny(detectedEdges, detectedEdges, size, size/3, 3, false);
        return detectedEdges;
    }

    public static Mat Laplacian(Mat source, int size, int delta){

        int ddepth = CvType.CV_16S;

        Mat abs_dst,dst;

        Imgproc.GaussianBlur(source, source, new Size(3.0, 3.0), 0);

        Imgproc.GaussianBlur(source, source, new Size(3, 3), 0, 0,  Imgproc.BORDER_DEFAULT);
        //cvtColor( src, gray, CV_RGB2GRAY );

        /// Apply Laplace function
        Imgproc.Laplacian(source, source, CvType.CV_16S, size, 5, delta, Imgproc.BORDER_DEFAULT);
        return source;
    }

    public static Mat Sobel(Mat source, int delta ){

        Mat grey = new Mat();
        Imgproc.cvtColor(source, grey, Imgproc.COLOR_BGR2GRAY);
        Mat sobelx = new Mat();
        Imgproc.Sobel(grey, sobelx, CvType.CV_32F, 1, delta);

        double minVal, maxVal;
        Core.MinMaxLocResult minMaxLocResult=Core.minMaxLoc(sobelx);
        minVal=minMaxLocResult.minVal;
        maxVal=minMaxLocResult.maxVal;

        Mat draw = new Mat();
        sobelx.convertTo(draw, CvType.CV_8U, 255.0 / (maxVal - minVal), -minVal * 255.0 / (maxVal - minVal));
        return draw;
    }


    public static Mat kmeans(Mat src){

        Mat mHSV = new Mat();
        Imgproc.cvtColor(src, mHSV, Imgproc.COLOR_RGBA2RGB,3);
        Imgproc.cvtColor(src, mHSV, Imgproc.COLOR_RGB2HSV,3);
        List<Mat> hsv_planes = new ArrayList<Mat>(3);
        Core.split(mHSV, hsv_planes);

        Mat channel = hsv_planes.get(0);
        channel = Mat.zeros(mHSV.rows(),mHSV.cols(),CvType.CV_8UC1);
        hsv_planes.set(2,channel);
        Core.merge(hsv_planes,mHSV);

        Mat clusteredHSV = new Mat();
        mHSV.convertTo(mHSV, CvType.CV_32FC3);
        TermCriteria criteria = new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER,200,0.1);
        Core.kmeans(mHSV, 3, clusteredHSV, criteria, 30, Core.KMEANS_PP_CENTERS);

        return mHSV;
    }

    public static Mat thresholding(Mat thresholdImg1, int minValue, int maxValue){

        Mat rgba =thresholdImg1; Mat tempMat = thresholdImg1;
        rgba = new Mat(thresholdImg1.cols(), thresholdImg1.rows(), CvType.CV_8UC3);
        thresholdImg1.copyTo(rgba);

        List<Mat> hsv_planes_temp = new ArrayList<Mat>(3);
        Core.split(tempMat, hsv_planes_temp);



        Mat mHSV = new Mat();
        Imgproc.cvtColor(rgba, mHSV, Imgproc.COLOR_RGBA2RGB,3);
        Imgproc.cvtColor(rgba, mHSV, Imgproc.COLOR_RGB2HSV,3);
        List<Mat> hsv_planes = new ArrayList<Mat>(3);
        Core.split(mHSV, hsv_planes);




        Mat channel = hsv_planes.get(0);
        channel = Mat.zeros(mHSV.rows(),mHSV.cols(),CvType.CV_8UC1);
        hsv_planes.set(2,channel);
        Core.merge(hsv_planes,mHSV);

        //mHSV.convertTo(mHSV, CvType.CV_8UC1);
        //mHSV = Histogram(mHSV);





        Mat clusteredHSV = new Mat();
        mHSV.convertTo(mHSV, CvType.CV_32FC3);
        TermCriteria criteria = new TermCriteria(TermCriteria.EPS + TermCriteria.MAX_ITER,100,0.1);
        Core.kmeans(mHSV, 1, clusteredHSV, criteria, 20, Core.KMEANS_PP_CENTERS);
        Mat hsvImg = new Mat();
        List<Mat> hsvPlanes = new ArrayList<>();
        Mat thresholdImg = new Mat();
        int thresh_type = Imgproc.THRESH_BINARY_INV;
        hsvImg.create(mHSV.size(), CvType.CV_8U);
        Imgproc.cvtColor(mHSV, hsvImg, Imgproc.COLOR_BGR2HSV);
        Core.split(hsvImg, hsvPlanes);
        Imgproc.threshold(hsvPlanes.get(1), thresholdImg, 0 , 200 , thresh_type);



        Imgproc.threshold(mHSV,mHSV, 100, 250, Imgproc.THRESH_BINARY );





        Mat foreground = new Mat(mHSV.size(), CvType.CV_8UC3, new Scalar(255, 255, 255));
        Core.bitwise_not(mHSV,foreground);

        mHSV.convertTo(mHSV, CvType.CV_8UC1);

        mHSV.copyTo(foreground, mHSV);
        //mHSV.convertTo(mHSV, CvType.CV_8UC1);
        return foreground;
    }



    public static Mat Histogram(Mat im){

        Mat img = im;

        Mat equ = new Mat();
        img.copyTo(equ);
        //Imgproc.blur(equ, equ, new Size(3, 3));

        Imgproc.cvtColor(equ, equ, Imgproc.COLOR_BGR2YCrCb);
        List<Mat> channels = new ArrayList<Mat>();
        Core.split(equ, channels);
        Imgproc.equalizeHist(channels.get(0), channels.get(0));
        Core.merge(channels, equ);
        Imgproc.cvtColor(equ, equ, Imgproc.COLOR_YCrCb2BGR);

        Mat gray = new Mat();
        Imgproc.cvtColor(equ, gray, Imgproc.COLOR_BGR2GRAY);
        Mat grayOrig = new Mat();
        Imgproc.cvtColor(img, grayOrig, Imgproc.COLOR_BGR2GRAY);
        System.out.println("Histogram work ///");
        return grayOrig;
    }
}