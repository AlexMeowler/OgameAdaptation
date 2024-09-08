package org.retal.offgame.old.core.buildings;

public class SpaceYard extends Building 
{
	public SpaceYard(String name)
	{
		super(name);
		base_cost[0] = 400;
		base_cost[1] = 200;
		base_cost[2] = 100;
		required_buildings[Building.ROBOT_FACTORY] = 2;
	}
	
	public SpaceYard(int level, String name)
	{
		super(level, name);
		base_cost[0] = 400;
		base_cost[1] = 200;
		base_cost[2] = 100;
		required_buildings[Building.ROBOT_FACTORY] = 2;
	}
	public String generateDescription(double[] current_resources)
	{
		return "В строительной верфи производятся все виды кораблей и оборонительных сооружений. Увеличение уровня повышает скорость постройки кораблей и обороны.<br>" + super.generateDescription(current_resources);
	}
}
