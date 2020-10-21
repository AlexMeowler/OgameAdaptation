package adaptogame.core;

import static java.lang.Math.*;

public class MetalMines extends Building 
{
	public MetalMines(String name)
	{
		super(name);
		required_buildings[Building.POWER_STATION] = 1;
	}
	
	public MetalMines(int level, String name)
	{
		super(level, name);
		required_buildings[Building.POWER_STATION] = 1;
	}
	
	public double calcGathering()
	{
		return 30 * level * pow(1.1, level) * RESOURCE_PRODUCTION_MULTIPLIER;
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
		double[] d = new double[4];
		d[0] = 60 * pow(1.5, level);
		d[1] = 15 * pow(1.5, level);
		d[2] = 0;
		d[3] = 0;
		return d;
	}
	
	public String generateEnergyChange()
	{
		return " (<font color='red'>-" + (int)calcDifference(level, level + 1) + " Энергия</font>)";
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Основной поставщик сырья для строительства несущих структур построек и кораблей.<br>" + super.generateDescription(current_resources);
	}
}
