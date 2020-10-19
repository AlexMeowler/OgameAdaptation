package offgame;

public class ComputerTechnology extends Technology 
{
	public ComputerTechnology(String name)
	{
		super(name);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
	}
	
	public ComputerTechnology(int level, String name)
	{
		super(level, name);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "С увеличением мощности компьютеров можно командовать всё большим количеством флотов. Каждый уровень компьютерной технологии увеличивает максимальное количество флотов на один.<br>" + super.generateDescription(current_resources);
	}
}
