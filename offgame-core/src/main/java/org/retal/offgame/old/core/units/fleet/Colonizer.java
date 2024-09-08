package org.retal.offgame.old.core.units.fleet;

import org.retal.offgame.old.core.EngineCategory;
import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class Colonizer extends Unit
{
	public Colonizer(String name) 
	{
		super(name);
		cost[0] = 10000;
		cost[1] = 20000;
		cost[2] = 10000;
		structure = 3000;
		shields = 100;
		attack_power = 50;
		cargo_volume = 7500;
		engine_type = EngineCategory.IMPULSE;
		speed = 2500;
		fuel_consumption = 1000;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.IMPULSE_ENGINE] = 3;
		required_technologies[Technology.COLONIZATION_TECHNOLOGY] = 1;
	}
	
	public Colonizer(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 10000;
		cost[1] = 20000;
		cost[2] = 10000;
		structure = 3000;
		shields = 100;
		attack_power = 50;
		cargo_volume = 7500;
		engine_type = EngineCategory.IMPULSE;
		speed = 2500;
		fuel_consumption = 1000;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.IMPULSE_ENGINE] = 3;
		required_technologies[Technology.COLONIZATION_TECHNOLOGY] = 1;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "При помощи этого корабля можно осваивать незаселённые планеты.<br>" + super.generateDescription(current_resources);
	}
}
