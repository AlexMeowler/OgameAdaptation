package org.retal.offgame.old.core.buildings;

public class RobotFactory extends Building
{
	public RobotFactory(String name)
	{
		super(name);
		base_cost[0] = 400;
		base_cost[1] = 120;
		base_cost[2] = 200;
		required_buildings[Building.DEITERIUM_MINES] = 2;
	}
	
	public RobotFactory(int level, String name)
	{
		super(level, name);
		base_cost[0] = 400;
		base_cost[1] = 120;
		base_cost[2] = 200;
		required_buildings[Building.DEITERIUM_MINES] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Предоставляет простую рабочую силу, которую можно применять при строительстве планетарной инфраструктуры. Каждый уровень развития фабрики повышает скорость строительства зданий.<br>" + super.generateDescription(current_resources);
	}
}
