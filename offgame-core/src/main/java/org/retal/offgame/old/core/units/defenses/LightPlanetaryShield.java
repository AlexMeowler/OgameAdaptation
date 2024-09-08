package org.retal.offgame.old.core.units.defenses;

import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class LightPlanetaryShield extends Unit 
{
	public LightPlanetaryShield(String name)
	{
		super(name);
		structure = 2000;
		shields = 2000;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 1;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 2;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 10000;
		cost[1] = 10000;
	}
	
	public LightPlanetaryShield(String name, int amount) 
	{
		super(name, amount);
		structure = 2000;
		shields = 2000;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 1;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 2;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 10000;
		cost[1] = 10000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Защищает планету от разграблений и поглощает урон от атак попавших в купол.<br>" + super.generateDescription(current_resources);
	}	
}
