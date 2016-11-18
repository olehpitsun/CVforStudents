package task1;

import task1.Filters.*;
import task1.interfaces.Filter;

/**
 * Created by oleh on 18.11.2016.
 */

public class FilterFactory {

    public Filter getFilter(String currentFilter){

        switch (currentFilter){
            case "Bilateral":
                return new BilateralFilter();
            case "AdaptiveBilateral":
                return new adaptiveBilateralFilter();
            case "Blur":
                return new BlurFilter();
            case "Gaussian":
                return new GaussianFilter();
            case "Median":
                return new MedianFilter();
            default:
                break;
        }
        return null;
    }
}
