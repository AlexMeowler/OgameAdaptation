package adaptogame.core;

public class ColonizationTechnology extends Technology
{
	public ColonizationTechnology(String name)
	{
		super(name);
		base_cost[0] = 1000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		base = 3;
		required_buildings[Building.LABORATORY] = 3;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.METALLURGY] = 2;
	}
	
	public ColonizationTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 1000;
		base_cost[1] = 4000; 
		base_cost[2] = 1000;
		base = 3;
		required_buildings[Building.LABORATORY] = 3;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 5;
		required_technologies[Technology.METALLURGY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Эта технология позволит вам расширить вашу империю до 20 колоний. Каждый уровень позволяет контролировать ещё одну колонию.<br>" + super.generateDescription(current_resources);
	}
}
