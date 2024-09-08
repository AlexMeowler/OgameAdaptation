package org.retal.offgame.old.core.technologies;

import org.retal.offgame.old.core.buildings.Building;

public class Metallurgy extends Technology
{
	public Metallurgy(String name)
	{
		super(name);
		base_cost[0] = 1000;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 2;
	}
	
	public Metallurgy(int level, String name)
	{
		super(level, name);
		base_cost[0] = 1000;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Специальные сплавы улучшают броню космических кораблей и оборонительных сооружений, повышая их живучесть. Каждый уровень усиливает структуру кораблей и обороны на 10%.<br>" + super.generateDescription(current_resources);
	}
}
