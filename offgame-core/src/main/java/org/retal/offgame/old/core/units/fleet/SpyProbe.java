package org.retal.offgame.old.core.units.fleet;

import org.retal.offgame.old.core.EngineCategory;
import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class SpyProbe extends Unit
{
	public SpyProbe(String name) 
	{
		super(name);
		cost[0] = 3000;
		cost[1] = 1000;
		structure = 100;
		engine_type = EngineCategory.REACTIVE;
		speed = 100000000;
		fuel_consumption = 1;
		cargo_volume = 5;
		required_buildings[Building.SPACE_YARD] = 3;
		required_technologies[Technology.REACTIVE_ENGINE] = 3;
		required_technologies[Technology.ESPIONAGE] = 2;
	}
	
	public SpyProbe(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 3000;
		cost[1] = 1000;
		structure = 100;
		engine_type = EngineCategory.REACTIVE;
		speed = 100000000;
		fuel_consumption = 1;
		cargo_volume = 5;
		required_buildings[Building.SPACE_YARD] = 3;
		required_technologies[Technology.REACTIVE_ENGINE] = 3;
		required_technologies[Technology.ESPIONAGE] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Шпионские зонды - это маленькие манёвренные корабли, которые доставляют с больших расстояний данные о флотах и планетах.<br>" + super.generateDescription(current_resources);
	}
}
