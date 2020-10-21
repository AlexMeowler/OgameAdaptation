package adaptogame.core;

import static java.lang.Math.pow;

public class CrystalMines extends Building
{
	public CrystalMines(String name)
	{
		super(name);
		required_buildings[Building.METAL_MINES] = 2;
	}
	
	public CrystalMines(int level, String name)
	{
		super(level, name);
		required_buildings[Building.METAL_MINES] = 2;
	}
	
	public double calcGathering()
	{
		return 20 * level * pow(1.1, level) * RESOURCE_PRODUCTION_MULTIPLIER;
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
		d[0] = 48 * pow(1.6, level);
		d[1] = 24 * pow(1.6, level);
		d[2] = 0;
		d[3] = 0;
		return d;
	}
	
	public String generateEnergyChange()
	{
		return " (<font color='red'>-" + (int)calcDifference(level, level + 1) + " Энергия</font>)";
	}
	
	public String generateHeader()
	{
		return generateHeaderWithoutLevel() + " " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Основной поставщик сырья для электронных строительных элементов и сплавов.<br>" + super.generateDescription(current_resources);
	}
}
