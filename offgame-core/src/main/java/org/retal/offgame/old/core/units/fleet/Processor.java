package org.retal.offgame.old.core.units.fleet;

import org.retal.offgame.old.core.EngineCategory;
import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class Processor extends Unit
{
	public Processor(String name) 
	{
		super(name);
		cost[0] = 10000;
		cost[1] = 6000;
		cost[2] = 2000;
		structure = 1600;
		shields = 10;
		attack_power = 1;
		cargo_volume = 20000;
		engine_type = EngineCategory.REACTIVE;
		speed = 2000;
		fuel_consumption = 300;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.REACTIVE_ENGINE] = 6;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 2;
	}
	
	public Processor(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 10000;
		cost[1] = 6000;
		cost[2] = 2000;
		structure = 1600;
		shields = 10;
		attack_power = 1;
		cargo_volume = 20000;
		engine_type = EngineCategory.REACTIVE;
		speed = 2000;
		fuel_consumption = 300;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.REACTIVE_ENGINE] = 6;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "С помощью переработчика добывается сырьё из обломков.<br>" + super.generateDescription(current_resources);
	}
}
