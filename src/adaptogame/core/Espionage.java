package adaptogame.core;

public class Espionage extends Technology
{
	public Espionage(String name)
	{
		super(name);
		base_cost[0] = 200;
		base_cost[1] = 1000; 
		base_cost[2] = 200;
		required_buildings[Building.LABORATORY] = 3;
	}
	
	public Espionage(int level, String name)
	{
		super(level, name);
		base_cost[0] = 200;
		base_cost[1] = 1000; 
		base_cost[2] = 200;
		required_buildings[Building.LABORATORY] = 3;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "С помощью этой технологии добываются данные о чужих планетах. Влияет на информацию о нападающих вражеских флотах, на шанс обнаружения шпионских зондов.<br>" + super.generateDescription(current_resources);
	}
}
