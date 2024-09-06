package adaptogame.core.units.fleet;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class Cruiser extends Unit
{
	public Cruiser(String name) 
	{
		super(name);
		cost[0] = 20000;
		cost[1] = 7000;
		cost[2] = 2000;
		structure = 2700;
		shields = 50;
		attack_power = 400;
		cargo_volume = 800;
		engine_type = EngineCategory.IMPULSE;
		speed = 15000;
		fuel_consumption = 300;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[LIGHT_FIGHTER] = 6;
		rapid_fire[ROCKET_LAUNCHER] = 10;
		required_buildings[Building.SPACE_YARD] = 5;
		required_technologies[Technology.IMPULSE_ENGINE] = 4;
		required_technologies[Technology.ION_TECHNOLOGY] = 2;
	}
	
	public Cruiser(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 20000;
		cost[1] = 7000;
		cost[2] = 2000;
		structure = 2700;
		shields = 50;
		attack_power = 400;
		cargo_volume = 800;
		engine_type = EngineCategory.IMPULSE;
		speed = 15000;
		fuel_consumption = 300;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[LIGHT_FIGHTER] = 6;
		rapid_fire[ROCKET_LAUNCHER] = 10;
		required_buildings[Building.SPACE_YARD] = 5;
		required_technologies[Technology.IMPULSE_ENGINE] = 4;
		required_technologies[Technology.ION_TECHNOLOGY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Крейсеры почти втрое сильней защищены, чем тяжёлые истребители, а по огневой мощи они превосходят тяжёлые истребители почти в два раза. К тому же они очень быстры.<br>" + super.generateDescription(current_resources);
	}
}
