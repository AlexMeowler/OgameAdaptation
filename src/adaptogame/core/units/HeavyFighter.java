package adaptogame.core.units;

import adaptogame.core.EngineCategory;
import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;

public class HeavyFighter extends Unit
{
	public HeavyFighter(String name) 
	{
		super(name);
		cost[0] = 6000;
		cost[1] = 4000;
		structure = 1000;
		shields = 25;
		attack_power = 150;
		cargo_volume = 100;
		engine_type = EngineCategory.IMPULSE;
		speed = 10000;
		fuel_consumption = 75;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[DESTROYER] = 5;
		rapid_fire[SMALL_TRANSPORT] = 3;
		required_buildings[Building.SPACE_YARD] = 3;
		required_technologies[Technology.IMPULSE_ENGINE] = 2;
		required_technologies[Technology.METALLURGY] = 2;
	}
	
	public HeavyFighter(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 6000;
		cost[1] = 4000;
		structure = 1000;
		shields = 25;
		attack_power = 150;
		cargo_volume = 100;
		engine_type = EngineCategory.IMPULSE;
		speed = 10000;
		fuel_consumption = 75;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		rapid_fire[DESTROYER] = 5;
		rapid_fire[SMALL_TRANSPORT] = 3;
		required_buildings[Building.SPACE_YARD] = 3;
		required_technologies[Technology.IMPULSE_ENGINE] = 2;
		required_technologies[Technology.METALLURGY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Дальнейшее развитие лёгкого истребителя, он лучше защищён и обладает большей силой атаки.<br>" + super.generateDescription(current_resources);
	}
}
