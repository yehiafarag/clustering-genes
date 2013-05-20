/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package inf282ii.controller;

import inf282assignii.model.GlobalNormalization;
import inf282assignii.model.NewNormalisedRatios;
import inf282assignii.model.Transformation;
import inf282assignii.model.beans.GeneIntensitie;
import inf282assignii.model.beans.Point;
import inf282assignii.view.MAPlot;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Yehia Mokhtar
 */
public class Control {
    private Transformation tf =new Transformation();
    private MAPlot maplot = new MAPlot();
    private GlobalNormalization gn= new GlobalNormalization();
    private NewNormalisedRatios nnr = new NewNormalisedRatios();
    public Point[] onProcess()
    {
       GeneIntensitie[] giList =  this.dataGenerator();
       Point[] points =  tf.transform(giList);       
       maplot.generatPlot(points,"MA-Plot");

       return points;

    }


    public double performGlobaNormalization(Point[]  points)
    {
      ArrayList<Object> ol=  gn.globalNormalizing(points);
      Point[] norPoints = (Point[])ol.get(0);
      //maplot.generatPlot(norPoints,"Global Normalized MA-Plot");
      maplot.generat3DPlot(points, norPoints, null);
       return (Double)ol.get(1);

    }

    public Map<Integer,String>  calculateNewNormalisedRatios(double normalizationValue)
    {
        GeneIntensitie[] giList =  this.dataGenerator();
        Map<Integer,String>  ratiosMap = nnr.calculateNewNormalisedRatios(normalizationValue,giList);
        return ratiosMap;
    }

    private GeneIntensitie[] dataGenerator()
    {
       int[] data[] = {{20,40},{20, 50},{30, 60},{40, 20},{50, 70},{50, 90},{60, 120},{70, 90},{80, 110},{80, 260},{ 90, 70},{90, 130},{100, 110},{110, 130},{110, 150},{130, 170},{140, 70},{160, 180}};
       GeneIntensitie[] giList = new GeneIntensitie[data.length];
       int index = 0;
       for(int[] d:data )
       {
            GeneIntensitie gi = new GeneIntensitie();
            gi.setP(d[0]);
            gi.setQ(d[1]);
            giList[index] = gi;
            index++;
       }
       return giList;

    }

}
