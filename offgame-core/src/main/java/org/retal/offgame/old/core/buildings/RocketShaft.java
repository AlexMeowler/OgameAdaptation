package org.retal.offgame.old.core.buildings;

public class RocketShaft extends Building
{
	public RocketShaft(String name)
	{
		super(name);
		base_cost[0] = 20000;
		base_cost[1] = 20000;
		base_cost[2] = 1000;
		required_buildings[Building.SPACE_YARD] = 5;
	}
	
	public RocketShaft(int level, String name)
	{
		super(level, name);
		base_cost[0] = 20000;
		base_cost[1] = 20000;
		base_cost[2] = 1000;
		required_buildings[Building.SPACE_YARD] = 5;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Служит для хранения ракет. Увеличение уровня позволяет хранить больше ракет.<br>" + super.generateDescription(current_resources);
	}
}
