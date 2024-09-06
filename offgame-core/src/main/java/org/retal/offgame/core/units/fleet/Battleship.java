package org.retal.offgame.core.units.fleet;

import org.retal.offgame.core.EngineCategory;
import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.technologies.Technology;
import org.retal.offgame.core.units.Unit;

public class Battleship extends Unit
{
	public Battleship(String name) 
	{
		super(name);
		cost[0] = 45000;
		cost[1] = 15000;
		structure = 6000;
		shields = 200;
		attack_power = 1000;
		cargo_volume = 1500;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 10000;
		fuel_consumption = 500;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 7;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 4;
	}
	
	public Battleship(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 10000;
		cost[1] = 6000;
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
		return "Линкоры как правило составляют основу флота. Их тяжёлые орудия, высокая скорость и большой грузовой тоннаж делают их серьёзными противниками.<br>" + super.generateDescription(current_resources);
	}
}
