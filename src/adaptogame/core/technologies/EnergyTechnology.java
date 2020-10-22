package adaptogame.core.technologies;

import adaptogame.core.buildings.Building;

public class EnergyTechnology extends Technology 
{
	public EnergyTechnology(String name)
	{
		super(name);
		base_cost[0] = 0;
		base_cost[1] = 800; 
		base_cost[2] = 400;
		required_buildings[Building.LABORATORY] = 1;
	}
	
	public EnergyTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 0;
		base_cost[1] = 800; 
		base_cost[2] = 400;
		required_buildings[Building.LABORATORY] = 1;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Обладание различными видами энергии необходимо для многих новых технологий. Так же каждый уровень увеличивает выработку энергии на Термоядерных электростанциях.<br>" + super.generateDescription(current_resources);
	}
}
