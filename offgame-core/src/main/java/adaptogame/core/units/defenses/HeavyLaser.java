package adaptogame.core.units.defenses;

import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class HeavyLaser extends Unit 
{
	public HeavyLaser(String name)
	{
		super(name);
		structure = 800;
		shields = 100;
		attack_power = 250;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
		required_technologies[Technology.LASER_TECHNOLOGY] = 6;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 6000;
		cost[1] = 2000;
	}
	
	public HeavyLaser(String name, int amount) 
	{
		super(name, amount);
		structure = 800;
		shields = 100;
		attack_power = 250;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
		required_technologies[Technology.LASER_TECHNOLOGY] = 6;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 6000;
		cost[1] = 2000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Тяжёлый лазер представляет собой дальнейшее развитие лёгкого лазера.<br>" + super.generateDescription(current_resources);
	}
}
