package org.retal.offgame.core.units.fleet;

import org.retal.offgame.core.EngineCategory;
import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.technologies.Technology;
import org.retal.offgame.core.units.Unit;

public class SmallTransport extends Unit 
{
	public SmallTransport(String name) 
	{
		super(name);
		cost[0] = 2000;
		cost[1] = 2000;
		structure = 400;
		shields = 10;
		attack_power = 5;
		cargo_volume = 5000;
		engine_type = EngineCategory.REACTIVE;
		speed = 5000;
		fuel_consumption = 20;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 2;
		required_technologies[Technology.REACTIVE_ENGINE] = 2;
	}
	
	public SmallTransport(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 2000;
		cost[1] = 2000;
		structure = 400;
		shields = 10;
		attack_power = 5;
		cargo_volume = 5000;
		engine_type = EngineCategory.REACTIVE;
		speed = 5000;
		fuel_consumption = 20;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 2;
		required_technologies[Technology.REACTIVE_ENGINE] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Малый транспорт - это манёвренный корабль, который может быстро транспортировать сырьё на другие планеты.<br>" + super.generateDescription(current_resources);
	}
}
