package offgame;

import static java.lang.Math.pow;

public class NuclearStation extends Building 
{
	public NuclearStation()
	{
		super();
	}
	
	public NuclearStation(int level)
	{
		super(level);
	}
	
	public double calcGathering(int temp)
	{
		return 30 * level * pow(1.05 + temp * 0.01, level);
	
	}
	public double calcConsuming()
	{
		return 10 * level * pow(1.1, level);
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
