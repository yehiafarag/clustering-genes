package inf282assignii.model;

import inf282assignii.model.beans.GeneIntensitie;
import inf282assignii.model.beans.Point;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

	/**
	 *this class is used to calculate the transformation values for p and q 
	 *into ai and mi in order to plot them in MAPlot
	 * 
	 * 
	 */

public class Transformation {

   private ArrayList<Double> A = new ArrayList<Double>();
   private ArrayList<Double> M = new ArrayList<Double>();
   private DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.ENGLISH);
     
    
	public Point[] transform(GeneIntensitie[] giArr)
	{
		Point[] points = new Point[giArr.length];
		for(int index =0;index < giArr.length;index++)
		{
		
			Point p = new Point(calcA(giArr[index].getP(),giArr[index].getQ()),calcM(giArr[index].getP(),giArr[index].getQ()));
			points[index] = p;
		}
                System.out.println("A are : "+A);
                System.out.println("M are : "+M);
		return points;
	}
        private double calcA(int p, int q) {
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat  df =  new DecimalFormat("#.##",otherSymbols);
            double a = (double) (log2(Double.valueOf((double) p * (double) q))) / 2.0;
            a=Double.valueOf(df.format(a));
            A.add(a);
            return a;
        }

        private double calcM(int p,int q)
	{
            otherSymbols.setGroupingSeparator('.');
            DecimalFormat  df =  new DecimalFormat("#.##",otherSymbols);
            double m = (double)log2(Double.valueOf((double)p/(double)q));
           m =Double.valueOf(df.format(m));
            M.add(m);
            return m;
		
		
	}
	private double log2(double num)
	{
		return (double)(Math.log(num)/(double)Math.log(2));
	}

	

}
