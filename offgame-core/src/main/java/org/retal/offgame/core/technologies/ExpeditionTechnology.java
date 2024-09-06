package org.retal.offgame.core.technologies;

import org.retal.offgame.core.buildings.Building;

public class ExpeditionTechnology extends Technology 
{
	public ExpeditionTechnology(String name)
	{
		super(name);
		base_cost[0] = 4000;
		base_cost[1] = 8000; 
		base_cost[2] = 4000;
		base = 3;
		required_buildings[Building.LABORATORY] = 3;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 4;
		required_technologies[Technology.IMPULSE_ENGINE] = 3;
	}
	
	public ExpeditionTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 4000;
		base_cost[1] = 8000; 
		base_cost[2] = 4000;
		base = 3;
		required_buildings[Building.LABORATORY] = 3;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 4;
		required_technologies[Technology.IMPULSE_ENGINE] = 3;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Позволяет посылать экспедиции в неизведанные дали. Каждый уровень позволяет контролировать большее количество одновременных экспедиций.<br>" + super.generateDescription(current_resources);
	}
}
