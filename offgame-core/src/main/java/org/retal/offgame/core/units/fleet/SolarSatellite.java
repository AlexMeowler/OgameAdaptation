package org.retal.offgame.core.units.fleet;

import org.retal.offgame.core.buildings.Building;
import org.retal.offgame.core.units.Unit;

import static java.lang.Math.min;

public class SolarSatellite extends Unit 
{
	public SolarSatellite(String name) 
	{
		super(name);
		planet_max_temperature = 0;
		cost[1] = 2000;
		cost[2] = 500;
		structure = 200;
		shields = 10;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 1;
	}
	
	public SolarSatellite(String name, int planet_max_temperature) 
	{
		super(name);
		this.planet_max_temperature = planet_max_temperature;
		cost[1] = 2000;
		cost[2] = 500;
		structure = 200;
		shields = 10;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 1;
	}
	
	public SolarSatellite(String name, int amount, int planet_max_temperature) 
	{
		super(name, amount);
		this.planet_max_temperature = planet_max_temperature;
		cost[1] = 2000;
		cost[2] = 500;
		structure = 200;
		shields = 10;
		attack_power = 1;
		required_buildings[Building.SPACE_YARD] = 1;
	}
	
	public int calcElectricityProduction()
	{
		return min(planet_max_temperature / 4 + 20, 50);
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Платформы из солнечных батарей, которые находятся на высокой орбите. Они аккумулируют солнечную энергию. Чем жарче планета тем эффективнее работают солнечные спутники. На текущей планете один спутник генерирует ≈ <font color='lime'>" + calcElectricityProduction() + "</font> энергии.<br>" + super.generateDescription(current_resources);
	}
	
	private int planet_max_temperature;
}
