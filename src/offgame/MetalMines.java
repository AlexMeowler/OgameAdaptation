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
	
	public double calcDifference(int level1, int level2)
	{
		int save_level = level;
		level = level1;
		double first = calcConsuming();
		level = level2;
		double second = calcConsuming();
		level = save_level;
		return second - first;
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
