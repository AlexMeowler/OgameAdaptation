package org.retal.offgame.old.core.technologies;

import org.retal.offgame.old.core.buildings.Building;

public class GravitationalTechnology extends Technology
{
	public GravitationalTechnology(String name)
	{
		super(name);
		base_cost[0] = 0;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		base_cost[3] = 300000;
		base = 3;
		required_buildings[Building.LABORATORY] = 12;
	}
	
	public GravitationalTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 0;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		base_cost[3] = 300000;
		base = 3;
		required_buildings[Building.LABORATORY] = 12;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Гравитон - это частица, которая не обладает ни массой ни зарядом и определяет силу притяжения. Путём запуска концентрированного заряда гравитонов можно создавать искусственное гравитационное поле, которое, подобно чёрной дыре, втягивает в себя массу, благодаря чему можно уничтожать корабли или даже луны. Позволяет получить доступ к постройке самых совершенных корaблей и обороны.<br>" + super.generateDescription(current_resources);
	}
}
