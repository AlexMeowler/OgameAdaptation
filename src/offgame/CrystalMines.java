package offgame;

import static java.lang.Math.pow;

public class CrystalMines extends Building
{
	public CrystalMines()
	{
		super();
	}
	
	public CrystalMines(int level)
	{
		super(level);
	}
	
	public double calcGathering()
	{
		return 20 * level * pow(1.1, level);
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
		d[0] = 48 * pow(1.6, level);
		d[1] = 24 * pow(1.6, level);
		d[2] = 0;
		return d;
	}
}
