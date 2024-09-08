package org.retal.offgame.old.core.units.defenses;

import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class LightLaser extends Unit 
{
	public LightLaser(String name)
	{
		super(name);
		structure = 200;
		shields = 25;
		attack_power = 100;
		required_buildings[Building.SPACE_YARD] = 2;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 1;
		required_technologies[Technology.LASER_TECHNOLOGY] = 3;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 1500;
		cost[1] = 500;
	}
	
	public LightLaser(String name, int amount) 
	{
		super(name, amount);
		structure = 200;
		shields = 25;
		attack_power = 100;
		required_buildings[Building.SPACE_YARD] = 2;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 1;
		required_technologies[Technology.LASER_TECHNOLOGY] = 3;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 1500;
		cost[1] = 500;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "При помощи концентрированного обстрела цели фотонами можно достичь значительно бoльших разрушений, чем при применении обычного баллистического вооружения.<br>" + super.generateDescription(current_resources);
	}
}
