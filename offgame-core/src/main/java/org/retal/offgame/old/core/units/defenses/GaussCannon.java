package org.retal.offgame.old.core.units.defenses;

import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class GaussCannon extends Unit 
{
	public GaussCannon(String name)
	{
		super(name);
		structure = 3500;
		shields = 200;
		attack_power = 1100;
		required_buildings[Building.SPACE_YARD] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 6;
		required_technologies[Technology.WEAPON_TECHNOLOGY] = 3;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 1;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 20000;
		cost[1] = 15000;
		cost[2] = 2000;
	}
	
	public GaussCannon(String name, int amount) 
	{
		super(name, amount);
		structure = 3500;
		shields = 200;
		attack_power = 1100;
		required_buildings[Building.SPACE_YARD] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 6;
		required_technologies[Technology.WEAPON_TECHNOLOGY] = 3;
		required_technologies[Technology.SHIELD_TECHNOLOGY] = 1;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 20000;
		cost[1] = 15000;
		cost[2] = 2000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Пушка Гаусса ускоряет многотонные заряды с гигантскими затратами энергии.<br>" + super.generateDescription(current_resources);
	}
}
