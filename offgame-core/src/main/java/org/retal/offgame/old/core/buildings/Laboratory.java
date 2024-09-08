package org.retal.offgame.old.core.buildings;

public class Laboratory extends Building 
{
	public Laboratory(String name)
	{
		super(name);
		base_cost[0] = 200;
		base_cost[1] = 400;
		base_cost[2] = 200;
		required_buildings[Building.POWER_STATION] = 3;
	}
	
	public Laboratory(int level, String name)
	{
		super(level, name);
		base_cost[0] = 200;
		base_cost[1] = 400;
		base_cost[2] = 200;
		required_buildings[Building.POWER_STATION] = 3;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Необходима для исследования новых технологий. Увеличение уровня повышает скорость исследований.<br>" + super.generateDescription(current_resources);
	}
}
