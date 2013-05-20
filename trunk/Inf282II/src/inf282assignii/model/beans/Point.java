package inf282assignii.model.beans;

public class Point {
	
	private double[] point;
	
	public Point(double a, double m)
	{
		point = new double[]{a,m};
	}
	
	public  double[] getPoint()
	{
		return this.point;
	}
	

}
