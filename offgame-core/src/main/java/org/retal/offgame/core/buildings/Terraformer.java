package org.retal.offgame.core.buildings;

import org.retal.offgame.core.technologies.Technology;

public class Terraformer extends Building
{
	public Terraformer(String name)
	{
		super(name);
		base_cost[0] = 0;
		base_cost[1] = 50000;
		base_cost[2] = 100000;
		required_buildings[Building.NANITE_FACTORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 12;
	}
	
	public Terraformer(int level, String name)
	{
		super(level, name);
		base_cost[0] = 0;
		base_cost[1] = 50000;
		base_cost[2] = 100000;
		required_buildings[Building.NANITE_FACTORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 12;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Терраформер может преобразовывать огромные территории, делая их пригодными для застройки. Увеличивает количество полей на планете.<br>" + super.generateDescription(current_resources);
	}
}
