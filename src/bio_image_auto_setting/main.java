package bio_image_auto_setting;

import EvaluateMethods.FRAG;
import bio_image_auto_setting.storeData.DBConnect;
import bio_image_auto_setting.storeData.storeData;
import comparator.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lib.SQLDatabaseParam;
import objects.SegmentationResults;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import tools.ImageSettings;
import tools.StartImageParams;

import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh on 16.12.2016.
 */
public class main {

    public static ObservableList<SegmentationResults> segmentationResults = FXCollections.observableArrayList();

    public static String pathToImg = "C:\\bioimg\\testsegmentation\\watershed\\auto\\";
    public static String imgName = "TS_06_19_13_17_42";
    public static storeData storeData;

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        setConnectionValues();// connect to db
        storeData = new storeData(); //sql  connect

        /**
         * ОРИГІНАЛЬНЕ ЗОБРАЖЕННЯ
         */
        String imagePath = pathToImg + imgName + "\\" + imgName + ".bmp";
        Mat originalMat = Highgui.imread(imagePath); // зчитування зображення

        /**
         * ВХІДНІ ДАНІ ЗОБРАЖЕННЯ
         */
        StartImageParams stip = new StartImageParams();
        stip.getStartValues(originalMat);

        /**
         * ЕКСПЕРТНЕ ЗОБРАЖЕННІ
         */
        String expertImgPath = pathToImg + imgName + "\\" + "expert.png";
        Mat expertImgMat = Highgui.imread(expertImgPath); // зчитування зображення

        /**
         * ПІДІГНАТИ РОЗМІРИ ЗОБРАЖЕНЬ
         */

        ImageSettings imageSettings = new ImageSettings(originalMat, expertImgMat);
        Mat normalSegmentedImgMat = imageSettings.getResizedImageMat();

        Imgproc.cvtColor(expertImgMat, expertImgMat, Imgproc.COLOR_RGB2GRAY); // перетворення зобр в градації сірого

        List<Integer> lowTreshValue = Arrays.asList(75,80,85,90,95);

        int lastID = setInputValues(stip.getHistogramAverage(), stip.getRedValue(), stip.getGreenVlue(), stip.getBlueValue());

        for(int i = 0; i < lowTreshValue.size(); i++)
        {

            Mat newImageMat = new Mat();
            normalSegmentedImgMat.copyTo(newImageMat);

            /** ЗОБРАЖЕННЯ ПІСЛЯ ОБРОБКИ*/
            ImageManagerModule imageManagerModule = new ImageManagerModule();
            newImageMat = imageManagerModule.autoImageCorrection(newImageMat,lowTreshValue.get(i));

            /*** ПОРОГОВА СЕШМЕНТАЦІЯ
             * перетворення експертного зобр в градації сірого*/
            Imgproc.threshold(newImageMat, newImageMat, 200, 255, Imgproc.THRESH_BINARY);
            Highgui.imwrite(pathToImg + "thresh_" + lowTreshValue.get(i) + ".jpg", newImageMat);

            /*** ПОРІВНЯННЯ ЗОБРАЖЕНЬ*/
            System.out.println("\n====================\n lowTreshValue = " + lowTreshValue.get(i) );
            Main.main(newImageMat, expertImgMat);

            /** FRAG*/
            FRAG frag = new FRAG(newImageMat, expertImgMat);
            System.out.println("\nFRAG = " + frag.getResult());

            segmentationResults.add(new SegmentationResults(lastID, imgName, lowTreshValue.get(i), Main.getDistance(),frag.getResult()));
        }

        setOutputValues(); // додати в базу результати дослідів
    }



    /**
     * Додати дані в БД
     * вивести останній ід
     */
    private static int setInputValues(float histogramAverage, double redValue, double greenVlue, double blueValue) {
        return storeData.insertInputValues(histogramAverage, redValue, greenVlue, blueValue);
    }

    private static void setOutputValues() {
        for(int i = 0; i < segmentationResults.size(); i++ ){
            storeData.insertOutputValues(segmentationResults.get(i).lastID, segmentationResults.get(i).imgName,
                    segmentationResults.get(i).lowThresh, segmentationResults.get(i).distance, (double)segmentationResults.get(i).FRAG );
        }
    }

    /**
     * SET PARAMS TO
     * CONNECT TO DB
     */
    public static void setConnectionValues(){
        SQLDatabaseParam.setHost("localhost");
        SQLDatabaseParam.setPort("3306");
        SQLDatabaseParam.setDbuser("oleh");
        SQLDatabaseParam.setDbpass("oleh123");
        SQLDatabaseParam.setDbname("bioimageapp");

        DBConnect dbConnect = new DBConnect();
        dbConnect.checkDbConnection();
    }




}
