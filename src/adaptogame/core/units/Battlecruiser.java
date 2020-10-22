package adaptogame.core.units;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;

public class Battlecruiser extends Unit
{
	public Battlecruiser(String name) 
	{
		super(name);
		cost[0] = 30000;
		cost[1] = 40000;
		cost[2] = 15000;
		structure = 7000;
		shields = 400;
		attack_power = 700;
		cargo_volume = 750;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 10000;
		fuel_consumption = 250;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[SMALL_TRANSPORT] = 3;
		rapid_fire[LARGE_TRANSPORT] = 3;
		rapid_fire[HEAVY_FIGHTER] = 4;
		rapid_fire[CRUISER] = 4;
		rapid_fire[BATTLESHIP] = 7;
		required_buildings[Building.SPACE_YARD] = 8;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 5;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 5;
		required_technologies[Technology.LASER_TECHNOLOGY] = 5;
	}
	
	public Battlecruiser(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 30000;
		cost[1] = 40000;
		cost[2] = 15000;
		structure = 7000;
		shields = 400;
		attack_power = 700;
		cargo_volume = 750;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 10000;
		fuel_consumption = 250;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[SMALL_TRANSPORT] = 3;
		rapid_fire[LARGE_TRANSPORT] = 3;
		rapid_fire[HEAVY_FIGHTER] = 4;
		rapid_fire[CRUISER] = 4;
		rapid_fire[BATTLESHIP] = 7;
		required_buildings[Building.SPACE_YARD] = 8;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 5;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 5;
		required_technologies[Technology.LASER_TECHNOLOGY] = 5;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Линейный крейсер специализируется на перехвате вражеских флотов.<br>" + super.generateDescription(current_resources);
	}
}
