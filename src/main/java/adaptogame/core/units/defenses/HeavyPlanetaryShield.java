package adaptogame.core.units.defenses;

import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class HeavyPlanetaryShield extends Unit 
{
	public HeavyPlanetaryShield(String name)
	{
		super(name);
		structure = 10000;
		shields = 10000;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 6;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 6;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 50000;
		cost[1] = 50000;
	}
	
	public HeavyPlanetaryShield(String name, int amount) 
	{
		super(name, amount);
		structure = 10000;
		shields = 10000;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 6;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 6;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 50000;
		cost[1] = 50000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Дальнейшее развитие малого щитового купола. Он может сдерживать ещё более сильные атаки на планету, поглощая значительно большее количество энергии.<br>" + super.generateDescription(current_resources);
	}	
}
