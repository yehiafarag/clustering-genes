package inf282assignii.view;

import inf282assignii.model.beans.Point;
import java.awt.Color;
import javax.swing.JFrame;

import org.math.plot.*;




public class MAPlot {
	
	public void generatPlot(Point[] points,String type)
	{
		double[] XY[] = new double[points.length][];
                for(int index = 0; index < points.length;index++)
                {
                    Point p = points[index];
                    XY[index] = p.getPoint();
                }
				  // create your PlotPanel (you can use it as a JPanel)
				  Plot2DPanel plot = new Plot2DPanel();
				 
				  // add a line plot to the PlotPanel
				  plot.addScatterPlot("MA-Plot", XY);
				  plot.setAxisLabels(new String[]{"A","M"});
				  // put the PlotPanel in a JFrame, as a JPanel
				  JFrame frame = new JFrame(type);
				  frame.setContentPane(plot);
                                  frame.setSize(1000, 1000);
				  frame.setVisible(true);
		
	}
        public void generat3DPlot(Point[] points1,Point[] points2,String type)
	{
		double[] XY[] = new double[points1.length][];
                for(int index = 0; index < points1.length;index++)
                {
                    Point p = points1[index];
                    XY[index] = p.getPoint();
                }
                
		double[] XY2[] = new double[points2.length][];
                for(int index = 0; index < points2.length;index++)
                {
                    Point p = points2[index];
                    XY2[index] = p.getPoint();
                }
				  // create your PlotPanel (you can use it as a JPanel)
				  Plot2DPanel plot = new Plot2DPanel();
				 
				  // add a line plot to the PlotPanel

                                  plot.addScatterPlot("MA-Plot",Color.red, XY);
                                  plot.addScatterPlot("Normalized MA-Plot", Color.green, XY2);
				  
				  plot.setAxisLabels(new String[]{"A","M"});
				  // put the PlotPanel in a JFrame, as a JPanel
				  JFrame frame = new JFrame(type);
				  frame.setContentPane(plot);
                                  frame.setSize(1000, 1000);
				  frame.setVisible(true);
		
	}
      
     
}
