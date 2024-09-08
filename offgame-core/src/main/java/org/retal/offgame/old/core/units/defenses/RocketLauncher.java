package org.retal.offgame.old.core.units.defenses;

import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.units.Unit;

public class RocketLauncher extends Unit 
{
	public RocketLauncher(String name)
	{
		super(name);
		structure = 200;
		shields = 20;
		attack_power = 80;
		required_buildings[Building.SPACE_YARD] = 1;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 2000;
	}
	
	public RocketLauncher(String name, int amount) 
	{
		super(name, amount);
		structure = 200;
		shields = 20;
		attack_power = 80;
		required_buildings[Building.SPACE_YARD] = 1;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 2000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Ракетная установка - простое и дешёвое средство обороны.<br>" + super.generateDescription(current_resources);
	}
}
