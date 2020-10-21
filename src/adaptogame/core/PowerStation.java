package adaptogame.core;

import static java.lang.Math.pow;

public class PowerStation extends Building
{
	public PowerStation(String name)
	{
		super(name);
	}
	
	public PowerStation(int level, String name)
	{
		super(level, name);
	}
	
	public double calcGathering()
	{
		return 20 * level * pow(1.1, level);
	
	}
	
	public double[] calcBuildingCost() 
	{
		double[] d = new double[4];
		d[0] = 75 * pow(1.5, level);
		d[1] = 30 * pow(1.5, level);
		d[2] = 0;
		d[3] = 0;
		return d;
	}
	
	public String generateEnergyChange()
	{
		return " (<font color='lime'>+" + (int)calcDifference(level, level + 1) + " Энергия</font>)";
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Производит энергию из солнечных лучей.Энергия требуется для работы всех строений, производящих ресурсы.<br>" + super.generateDescription(current_resources);
	}
}
