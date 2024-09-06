package adaptogame.core.units.fleet;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class Bomber extends Unit
{
	public Bomber(String name) 
	{
		super(name);
		cost[0] = 50000;
		cost[1] = 25000;
		cost[2] = 15000;
		structure = 7500;
		shields = 500;
		attack_power = 1000;
		cargo_volume = 500;
		engine_type = EngineCategory.IMPULSE;
		speed = 4000;
		fuel_consumption = 1000;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[ROCKET_LAUNCHER] = 20;
		rapid_fire[LIGHT_LASER] = 20;
		rapid_fire[HEAVY_LASER] = 10;
		rapid_fire[ION_CANNON] = 10;
		required_buildings[Building.SPACE_YARD] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 5;
		required_technologies[Technology.IMPULSE_ENGINE] = 6;
	}
	
	public Bomber(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 50000;
		cost[1] = 25000;
		cost[2] = 15000;
		structure = 7500;
		shields = 500;
		attack_power = 1000;
		cargo_volume = 500;
		engine_type = EngineCategory.IMPULSE;
		speed = 4000;
		fuel_consumption = 1000;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[ROCKET_LAUNCHER] = 20;
		rapid_fire[LIGHT_LASER] = 20;
		rapid_fire[HEAVY_LASER] = 10;
		rapid_fire[ION_CANNON] = 10;
		required_buildings[Building.SPACE_YARD] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 5;
		required_technologies[Technology.IMPULSE_ENGINE] = 6;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Бомбардировщик был разработан специально для того, чтобы уничтожать планетарную защиту.<br>" + super.generateDescription(current_resources);
	}
}
