package org.retal.offgame.old.core.buildings;

import static java.lang.Math.pow;

public class DeiteriumMines extends Building
{
	public DeiteriumMines(String name, int temperature)
	{
		super(name);
		this.temperature = temperature;
		required_buildings[Building.METAL_MINES] = 4;
		required_buildings[Building.CRYSTAL_MINES] = 2;
	}
	
	public DeiteriumMines(int level, String name)
	{
		super(level, name);
		required_buildings[Building.METAL_MINES] = 4;
		required_buildings[Building.CRYSTAL_MINES] = 2;
	}
	
	public double calcGathering()
	{
		return 10 * level * pow(1.1, level) * (-0.002 * temperature + 1.28) * RESOURCE_PRODUCTION_MULTIPLIER;
	}
	
	public double calcConsuming()
	{
		return 20 * level * pow(1.1, level);
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
		d[0] = 225 * pow(1.5, level);
		d[1] = 75 * pow(1.5, level);
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
		return "Извлекает из воды на планете незначительную долю дейтерия.<br>" + super.generateDescription(current_resources);
	}
	
	private int temperature;
}
