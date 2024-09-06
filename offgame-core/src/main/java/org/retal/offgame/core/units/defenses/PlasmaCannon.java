package org.retal.offgame.core.units.defenses;

import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.technologies.Technology;
import org.retal.offgame.core.units.Unit;

public class PlasmaCannon extends Unit 
{
	public PlasmaCannon(String name)
	{
		super(name);
		structure = 10000;
		shields = 300;
		attack_power = 3000;
		required_buildings[Building.SPACE_YARD] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 7;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 50000;
		cost[1] = 50000;
		cost[2] = 30000;
	}
	
	public PlasmaCannon(String name, int amount) 
	{
		super(name, amount);
		structure = 10000;
		shields = 300;
		attack_power = 3000;
		required_buildings[Building.SPACE_YARD] = 8;
		required_technologies[Technology.PLASMA_TECHNOLOGY] = 7;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 50000;
		cost[1] = 50000;
		cost[2] = 30000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Лазерная технология была доведена до совершенства, ионная техника достигла конечной стадии.<br>" + super.generateDescription(current_resources);
	}	
}
