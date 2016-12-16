package task3;

import entity.Img;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;
import task3.interfaces.Segmentation;
import tools.ImageSettings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh on 21.11.2016.
 */
public class SegmentationMain {

    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        //вхідне зображення
        String imagePath = "C:\\bioimg\\testsegmentation\\watershed\\1\\1.jpg";
        String expertImagePath = "C:\\bioimg\\testsegmentation\\thresholding\\TS_06_19_13_17_42\\expert\\expert.png";



        Mat originalMat = Highgui.imread(imagePath);
        Mat expertMat = Highgui.imread(expertImagePath);


        ImageSettings imageSettings = new ImageSettings(originalMat, expertMat);

        Highgui.imwrite("C:\\bioimg\\testsegmentation\\watershed\\1\\newImg.bmp",
                imageSettings.getResizedImageMat());

        Img.setImg(imageSettings.getResizedImageMat());

        //preProcItems - список методів сегментації
        List<String>segmentationItems = new ArrayList<>();
        //segmentationItems.add("canny");
        //segmentationItems.add("laplacian");
        //segmentationItems.add("kmeans");
        //segmentationItems.add("threshold");
        segmentationItems.add("watershed");
        //segmentationItems.add("grabcut");

        SegmentationFactory segmentationFactory = new SegmentationFactory();
        for(String items : segmentationItems){
            Segmentation segmentation = segmentationFactory.getMethod(items);
            segmentation.getSegmentationMethod();
        }

/*
        try {
            CascadeClassifier faceDetector = new CascadeClassifier(SegmentationMain.class.getResource("C:\\Projects\\CVforStudents\\haarcascade_frontalface_alt.xml").getPath());

            Mat image = Highgui
                    .imread(SegmentationMain.class.getResource("C:\\Projects\\CVforStudents\\img\\11111.jpeg").getPath());
            MatOfRect faceDetections = new MatOfRect();
            faceDetector.detectMultiScale(image, faceDetections);

            System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));

            for (Rect rect : faceDetections.toArray()) {
                Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 255, 0));
            }

            String filename = "C:\\Projects\\CVforStudents\\img\\ouput.png";
            System.out.println(String.format("Writing %s", filename));
            Highgui.imwrite(filename, image);
        }catch (Exception e){
            System.out.println(e);
        }*/

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        /*Mat frame = Img.getImg();
        Imgproc.cvtColor(frame, frame, Imgproc.COLOR_RGB2GRAY);
        Imgproc.threshold(frame, frame, -1, 255, Imgproc.THRESH_BINARY_INV + Imgproc.THRESH_OTSU);

        Highgui.imwrite("C:\\Projects\\CVforStudents\\src\\1.jpg", frame);*/

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*
        int n = 7;

        int[] ar = {7,0,7,1,5,9,9};

        int[] left = new int[n];
        int[] right = new int[n];

        int water = 0;

        left[0] = ar[0];
        for(int i = 1; i < n; i++){
            left[i] = Math.max(left[i-1], ar[i]);
        }

        right[n-1] = ar[n-1];
        for(int i = n-2; i >=0; i--){
            right[i] =  Math.max(right[i+1],ar[i]);
        }



        for(int i = 0; i< n; i++){
            water += Math.min(left[i], right[i]) - ar[i];
        }

        System.out.println(water);
*/
/////////////////////////////////////////////////////////////////////////////////////////////





    }

}
