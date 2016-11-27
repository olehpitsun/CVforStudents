package task4;

import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

/**
 * Created by oleh on 24.11.2016.
 * by https://github.com/emchristiansen/OpenCVJavaDemo/blob/master/src/main/java/DetectFaceDemo.java
 */
public class DetectFaceDemo {
    public void run() {

        CascadeClassifier faceDetector = new CascadeClassifier("C:\\Projects\\CVforStudents\\haarcascade_frontalface_alt.xml");
        Mat image = Highgui.imread("C:\\Projects\\CVforStudents\\img\\input.png");

        MatOfRect faceDetections = new MatOfRect();
        faceDetector.detectMultiScale(image, faceDetections);

        System.out.println(String.format("Detected %s faces", faceDetections.toArray().length));



        Core.putText(image, "Detected " + faceDetections.toArray().length + " persons" , new Point(image.rows(),300),
                Core.FONT_HERSHEY_TRIPLEX, .7 ,new  Scalar(0,0,255));

        // Draw a bounding box around each face.
        int i = 0;
        for (Rect rect : faceDetections.toArray()) {

            Core.putText(image, "Find" , new Point(rect.x-20,rect.y),
                        Core.FONT_HERSHEY_TRIPLEX, .3 ,new  Scalar(255,255,255));

            Core.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(250, 0, 0), 2);
        }

        String filename = "C:\\Projects\\CVforStudents\\img\\output1.png";
        System.out.println(String.format("Writing %s", filename));
        Highgui.imwrite(filename, image);
    }
}


