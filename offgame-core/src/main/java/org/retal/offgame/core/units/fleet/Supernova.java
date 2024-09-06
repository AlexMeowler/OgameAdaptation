package org.retal.offgame.core.units.fleet;

import org.retal.offgame.core.EngineCategory;
import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.technologies.Technology;
import org.retal.offgame.core.units.Unit;

public class Supernova extends Unit
{
	public Supernova(String name) 
	{
		super(name);
		cost[0] = 4900000;
		cost[1] = 4900000;
		cost[2] = 2400000;
		structure = 980000;
		shields = 70000;
		attack_power = 600000;
		cargo_volume = 2000000;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 250;
		fuel_consumption = 550;
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
		rapid_fire[DESTROYER] = 5;
		rapid_fire[ROCKET_LAUNCHER] = 200;
		rapid_fire[LIGHT_LASER] = 200;
		rapid_fire[HEAVY_LASER] = 100;
		rapid_fire[GAUSS_CANNON] = 50;
		rapid_fire[ION_CANNON] = 100;
		required_buildings[Building.SPACE_YARD] = 13;
		required_buildings[Building.NANITE_FACTORY] = 3;
		required_buildings[Building.TERRAFORMER] = 1;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 8;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 9;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 11;
		required_technologies[Technology.METALLURGY] = 10;
		required_technologies[Technology.WEAPON_TECHNOLOGY] = 11;
		required_technologies[Technology.COLONIZATION_TECHNOLOGY] = 6;
		required_technologies[Technology.GRAVITY_TECHNOLOGY] = 2;
	}
	
	public Supernova(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 4900000;
		cost[1] = 4900000;
		cost[2] = 2400000;
		structure = 980000;
		shields = 70000;
		attack_power = 600000;
		cargo_volume = 2000000;
		engine_type = EngineCategory.HYPERSPACE;
		speed = 250;
		fuel_consumption = 550;
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
		rapid_fire[DESTROYER] = 5;
		rapid_fire[ROCKET_LAUNCHER] = 200;
		rapid_fire[LIGHT_LASER] = 200;
		rapid_fire[HEAVY_LASER] = 100;
		rapid_fire[GAUSS_CANNON] = 50;
		rapid_fire[ION_CANNON] = 100;
		required_buildings[Building.SPACE_YARD] = 13;
		required_buildings[Building.NANITE_FACTORY] = 3;
		required_buildings[Building.TERRAFORMER] = 1;
		required_technologies[Technology.HYPERSPACE_ENGINE] = 8;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 9;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 11;
		required_technologies[Technology.METALLURGY] = 10;
		required_technologies[Technology.WEAPON_TECHNOLOGY] = 11;
		required_technologies[Technology.COLONIZATION_TECHNOLOGY] = 6;
		required_technologies[Technology.GRAVITY_TECHNOLOGY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Самый совершенный военный корабль.<br>" + super.generateDescription(current_resources);
	}
}
