package adaptogame.core.technologies;

import adaptogame.core.buildings.Building;

public class LaserTechnology extends Technology
{
	public LaserTechnology(String name)
	{
		super(name);
		base_cost[0] = 200;
		base_cost[1] = 100; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 2;
	}
	
	public LaserTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 200;
		base_cost[1] = 100; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Благодаря фокусированию света возникает луч, который при попадании на объект наносит ему повреждения.<br>" + super.generateDescription(current_resources);
	}
}
