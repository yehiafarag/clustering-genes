/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf282assignii.model;

import inf282assignii.model.beans.Point;
import java.util.ArrayList;

/**
 *
 * @author Yehia Mokhtar
 *
 *
 * normalise the data using GlobalNormalization
 */
public class GlobalNormalization {


    public ArrayList<Object> globalNormalizing(Point[] points)
    {

        double mean =0.0;
        double calc = 0.0;
        Point[] normalizedData= new Point[points.length];
        for(Point point:points)
        {
            calc = calc + point.getPoint()[1];

        }
        mean= calc / (double)points.length;
        for(int index = 0;index < points.length;index++)
        {
            Point point = points[index];
            Point p = new Point((point.getPoint()[0]),( point.getPoint()[1]-mean));
            normalizedData[index] = p;

        }
        Double nv = this.getNormalisedValue(mean);
        ArrayList<Object> ol = new ArrayList<Object>();
        ol.add(normalizedData);
        ol.add(nv);
        return ol;
    }

    private double getNormalisedValue(double mean)
    {
        mean = -1*mean;
       // BigDecimal t = new BigDecimal(2);
        return Math.pow(2.0, mean);

    }

}
