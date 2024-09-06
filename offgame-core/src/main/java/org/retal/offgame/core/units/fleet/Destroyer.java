package org.retal.offgame.core.units.fleet;

import org.retal.offgame.core.EngineCategory;
import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.technologies.Technology;
import org.retal.offgame.core.units.Unit;

public class Destroyer extends Unit
{
	public Destroyer(String name) 
	{
		super(name);
		cost[0] = 45000;
		cost[1] = 30000;
		cost[2] = 15000;
		structure = 7500;
		shields = 100;
		attack_power = 800;
		cargo_volume = 600;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 10000;
		fuel_consumption = 500;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[DEATH_STAR] = 6;
		required_buildings[Building.SPACE_YARD] = 9;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 6;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 10;
	}
	
	public Destroyer(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 45000;
		cost[1] = 30000;
		cost[2] = 15000;
		structure = 7500;
		shields = 100;
		attack_power = 800;
		cargo_volume = 600;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 10000;
		fuel_consumption = 500;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[DEATH_STAR] = 6;
		required_buildings[Building.SPACE_YARD] = 9;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 6;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 10;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Эсминец - гроза Звёзд смерти.<br>" + super.generateDescription(current_resources);
	}
}
