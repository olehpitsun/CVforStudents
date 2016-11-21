package entity;

import org.opencv.core.Mat;

/**
 * Created by oleh on 18.11.2016.
 */
public class Img {
    public static Mat img;

    public static Mat getImg() {
        return img;
    }

    public static void setImg(Mat img1) {
        img = img1;
    }
}
