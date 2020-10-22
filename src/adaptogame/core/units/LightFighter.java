package adaptogame.core.units;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;

public class LightFighter extends Unit
{
	public LightFighter(String name) 
	{
		super(name);
		cost[0] = 3000;
		cost[1] = 1000;
		structure = 400;
		shields = 10;
		attack_power = 50;
		cargo_volume = 50;
		engine_type = EngineCategory.REACTIVE;
		speed = 12500;
		fuel_consumption = 20;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 1;
		required_technologies[Technology.REACTIVE_ENGINE] = 1;
	}
	
	public LightFighter(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 3000;
		cost[1] = 1000;
		structure = 400;
		shields = 10;
		attack_power = 50;
		cargo_volume = 50;
		engine_type = EngineCategory.REACTIVE;
		speed = 12500;
		fuel_consumption = 20;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 1;
		required_technologies[Technology.REACTIVE_ENGINE] = 1;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Лёгкий истребитель - это манёвренный корабль, который можно найти почти на каждой планете. Затраты на него не особо велики, однако щитовая мощность и вместимость очень малы.<br>" + super.generateDescription(current_resources);
	}
}
