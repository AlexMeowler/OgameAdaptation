package offgame;

public class Metallurgy extends Technology
{
	public Metallurgy()
	{
		super();
		base_cost[0] = 1000;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 2;
	}
	
	public Metallurgy(int level)
	{
		super(level);
		base_cost[0] = 1000;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 2;
	}
	
	public String generateHeader()
	{
		return "ћеталлурги€ " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "—пециальные сплавы улучшают броню космических кораблей и оборонительных сооружений, повыша€ их живучесть.  аждый уровень усиливает структуру кораблей и обороны на 10%.<br>" + super.generateDescription(current_resources);
	}
}
