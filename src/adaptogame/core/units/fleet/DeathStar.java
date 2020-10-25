package adaptogame.core.units.fleet;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class DeathStar extends Unit 
{
	public DeathStar(String name) 
	{
		super(name);
		cost[0] = 5000000;
		cost[1] = 4000000;
		cost[2] = 1000000;
		structure = 900000;
		shields = 50000;
		attack_power = 200000;
		cargo_volume = 1000000;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 100;
		fuel_consumption = 1;
		rapid_fire[SMALL_TRANSPORT] = 250;
		rapid_fire[LARGE_TRANSPORT] = 250;
		rapid_fire[LIGHT_FIGHTER] = 200;
		rapid_fire[HEAVY_FIGHTER] = 100;
		rapid_fire[CRUISER] = 33;
		rapid_fire[BATTLESHIP] = 30;
		rapid_fire[COLONIZER] = 250;
		rapid_fire[PROCESSOR] = 250;
		rapid_fire[BOMBER] = 25;
		rapid_fire[SPY_PROBE] = 1250;
		rapid_fire[OBLITERATOR] = 5;
		rapid_fire[BATTLECRUISER] = 15;
		rapid_fire[SOLAR_SATELLITE] = 1250;
		rapid_fire[DESTROYER] = 4;
		rapid_fire[ROCKET_LAUNCHER] = 200;
		rapid_fire[LIGHT_LASER] = 200;
		rapid_fire[HEAVY_LASER] = 100;
		rapid_fire[GAUSS_CANNON] = 50;
		rapid_fire[ION_CANNON] = 100;
		required_buildings[Building.SPACE_YARD] = 12;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 6;
		required_technologies[Technology.GRAVITY_TECHNOLOGY] = 1;
	}
	
	public DeathStar(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 5000000;
		cost[1] = 4000000;
		cost[2] = 1000000;
		structure = 900000;
		shields = 50000;
		attack_power = 200000;
		cargo_volume = 1000000;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 100;
		fuel_consumption = 1;
		rapid_fire[SMALL_TRANSPORT] = 250;
		rapid_fire[LARGE_TRANSPORT] = 250;
		rapid_fire[LIGHT_FIGHTER] = 200;
		rapid_fire[HEAVY_FIGHTER] = 100;
		rapid_fire[CRUISER] = 33;
		rapid_fire[BATTLESHIP] = 30;
		rapid_fire[COLONIZER] = 250;
		rapid_fire[PROCESSOR] = 250;
		rapid_fire[BOMBER] = 25;
		rapid_fire[SPY_PROBE] = 1250;
		rapid_fire[OBLITERATOR] = 5;
		rapid_fire[BATTLECRUISER] = 15;
		rapid_fire[SOLAR_SATELLITE] = 1250;
		rapid_fire[DESTROYER] = 4;
		rapid_fire[ROCKET_LAUNCHER] = 200;
		rapid_fire[LIGHT_LASER] = 200;
		rapid_fire[HEAVY_LASER] = 100;
		rapid_fire[GAUSS_CANNON] = 50;
		rapid_fire[ION_CANNON] = 100;
		required_buildings[Building.SPACE_YARD] = 12;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 6;
		required_technologies[Technology.GRAVITY_TECHNOLOGY] = 1;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Звезда смерти оснащена гигантской гравитонной пушкой, которая может уничтожать корабли и даже луны.<br>" + super.generateDescription(current_resources);
	}
}
