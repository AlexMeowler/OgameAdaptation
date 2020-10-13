package offgame;

import static java.lang.Math.pow;

public class PowerStation extends Building
{
	public PowerStation()
	{
		super();
	}
	
	public PowerStation(int level)
	{
		super(level);
	}
	
	public double calcGathering()
	{
		return 20 * level * pow(1.1, level);
	
	}
	public double calcConsuming()
	{
		return 0;
	}
	
	public double[] calcBuildingCost() 
	{
		double[] d = new double[3];
		d[0] = 75 * pow(1.5, level);
		d[1] = 30 * pow(1.5, level);
		d[2] = 0;
		return d;
	}
}
