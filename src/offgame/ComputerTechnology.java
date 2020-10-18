package offgame;

public class ComputerTechnology extends Technology 
{
	public ComputerTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
	}
	
	public ComputerTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
	}
	
	public String generateHeader()
	{
		return "Компьютерная технология " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "С увеличением мощности компьютеров можно командовать всё большим количеством флотов. Каждый уровень компьютерной технологии увеличивает максимальное количество флотов на один.<br>" + super.generateDescription(current_resources);
	}
}
