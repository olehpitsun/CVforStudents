package task4;

import org.opencv.core.Core;

/**
 * Created by oleh on 26.11.2016.
 */
public class FaceDetectionMain {
    public static void main(String[] args) {

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new DetectFaceDemo().run();
    }
}
