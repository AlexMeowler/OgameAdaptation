package offgame;

public class Espionage extends Technology
{
	public Espionage()
	{
		super();
		base_cost[0] = 200;
		base_cost[1] = 1000; 
		base_cost[2] = 200;
	}
	
	public Espionage(int level)
	{
		super(level);
		base_cost[0] = 200;
		base_cost[1] = 1000; 
		base_cost[2] = 200;
	}
	
	public String generateHeader()
	{
		return "Шпионаж " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "С помощью этой технологии добываются данные о чужих планетах. Влияет на информацию о нападающих вражеских флотах, на шанс обнаружения шпионских зондов.<br>" + super.generateDescription(current_resources);
	}
}
