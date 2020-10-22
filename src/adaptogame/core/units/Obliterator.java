package adaptogame.core.units;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;

public class Obliterator extends Unit
{
	public Obliterator(String name) 
	{
		super(name);
		cost[0] = 60000;
		cost[1] = 50000;
		cost[2] = 15000;
		structure = 11000;
		shields = 500;
		attack_power = 2000;
		cargo_volume = 2000;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 5000;
		fuel_consumption = 1000;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[BATTLECRUISER] = 2;
		rapid_fire[LIGHT_LASER] = 10;
		required_buildings[Building.SPACE_YARD] = 9;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 6;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 5;
	}
	
	public Obliterator(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 60000;
		cost[1] = 50000;
		cost[2] = 15000;
		structure = 11000;
		shields = 500;
		attack_power = 2000;
		cargo_volume = 2000;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 5000;
		fuel_consumption = 1000;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[BATTLECRUISER] = 2;
		rapid_fire[LIGHT_LASER] = 10;
		required_buildings[Building.SPACE_YARD] = 9;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 6;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 5;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Уничтожитель - король среди военных кораблей.<br>" + super.generateDescription(current_resources);
	}
}
