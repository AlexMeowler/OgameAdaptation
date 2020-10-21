package adaptogame.core;

public class ShieldTechnology extends Technology
{
	public ShieldTechnology(String name)
	{
		super(name);
		base_cost[0] = 200;
		base_cost[1] = 600; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
	}
	
	public ShieldTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 200;
		base_cost[1] = 600; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Занимается изучением более новых возможностей большего энергоснабжения щитов, что делает их эффективней и устойчивей. Благодаря этому, с каждым уровнем эффективность щитов кораблей и обороны повышается на 10%.<br>" + super.generateDescription(current_resources);
	}
}
