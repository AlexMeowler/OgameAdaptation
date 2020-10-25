package adaptogame.core.units.defenses;

import adaptogame.core.buildings.Building;
import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;

public class PlanetaryDefense extends Unit 
{
	public PlanetaryDefense(String name)
	{
		super(name);
		structure = 1500000;
		shields = 100000;
		attack_power = 100000;
		required_buildings[Building.SPACE_YARD] = 13;
		required_buildings[Building.NANITE_FACTORY] = 3;
		required_buildings[Building.TERRAFORMER] = 1;
		required_technologies[Technology.GRAVITY_TECHNOLOGY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 12;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 9;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 11;
		required_technologies[Technology.METALLURGY] = 10;
		required_technologies[Technology.WEAPON_TECHNOLOGY] = 11;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 10000000;
		cost[1] = 5000000;
		cost[2] = 2500000;
	}
	
	public PlanetaryDefense(String name, int amount) 
	{
		super(name, amount);
		structure = 1500000;
		shields = 100000;
		attack_power = 100000;
		required_buildings[Building.SPACE_YARD] = 13;
		required_buildings[Building.NANITE_FACTORY] = 3;
		required_buildings[Building.TERRAFORMER] = 1;
		required_technologies[Technology.GRAVITY_TECHNOLOGY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 12;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 9;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 11;
		required_technologies[Technology.METALLURGY] = 10;
		required_technologies[Technology.WEAPON_TECHNOLOGY] = 11;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 10000000;
		cost[1] = 5000000;
		cost[2] = 2500000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Cовершенная защита, пробить которую будет трудно даже самым опасным кораблям галактики.<br>" + super.generateDescription(current_resources);
	}	
}
