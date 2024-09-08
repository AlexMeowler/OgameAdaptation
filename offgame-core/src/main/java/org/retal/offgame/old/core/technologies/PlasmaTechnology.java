package org.retal.offgame.old.core.technologies;

import org.retal.offgame.old.core.buildings.Building;

public class PlasmaTechnology extends Technology
{
	public PlasmaTechnology(String name)
	{
		super(name);
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		required_buildings[Building.LABORATORY] = 5;
		required_technologies[Technology.LASER_TECHNOLOGY] = 10;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 8;
		required_technologies[Technology.ION_TECHNOLOGY] = 5;
	}
	
	public PlasmaTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		required_buildings[Building.LABORATORY] = 5;
		required_technologies[Technology.LASER_TECHNOLOGY] = 10;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 8;
		required_technologies[Technology.ION_TECHNOLOGY] = 5;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Дальнейшее развитие ионной технологии, которая ускоряет не ионы, а высокоэнергетическую плазму. Она оказывает опустошительное действие при попадании на какой-либо объект.<br>" + super.generateDescription(current_resources);
	}
}
