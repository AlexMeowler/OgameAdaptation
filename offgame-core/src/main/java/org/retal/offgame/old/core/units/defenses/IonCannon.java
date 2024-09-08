package org.retal.offgame.old.core.units.defenses;

import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class IonCannon extends Unit 
{
	public IonCannon(String name)
	{
		super(name);
		structure = 800;
		shields = 500;
		attack_power = 150;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.ION_TECHNOLOGY] = 4;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 2000;
		cost[1] = 6000;
	}
	
	public IonCannon(String name, int amount) 
	{
		super(name, amount);
		structure = 800;
		shields = 500;
		attack_power = 150;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.ION_TECHNOLOGY] = 4;
		rapid_fire[Unit.SPY_PROBE] = 5;
		cost[0] = 2000;
		cost[1] = 6000;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Ионное орудие направляет на цель волну ионов, которая дестабилизирует щиты и повреждает электронику.<br>" + super.generateDescription(current_resources);
	}
}
