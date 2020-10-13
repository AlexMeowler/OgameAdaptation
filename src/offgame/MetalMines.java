package offgame;

import static java.lang.Math.*;

public class MetalMines extends Building 
{
	public MetalMines()
	{
		super();
	}
	
	public MetalMines(int level)
	{
		super(level);
	}
	
	public double calcGathering()
	{
		return 30 * level * pow(1.1, level);
	}
	
	public double calcConsuming()
	{
		return 10 * level * pow(1.1, level);
	}
	
	public double[] calcBuildingCost() 
	{
		double[] d = new double[3];
		d[0] = 60 * pow(1.5, level);
		d[1] = 15 * pow(1.5, level);
		d[2] = 0;
		return d;
	}
}
