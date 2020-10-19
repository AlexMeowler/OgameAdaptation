package offgame;

public class Laboratory extends Building 
{
	public Laboratory()
	{
		super();
		base_cost[0] = 200;
		base_cost[1] = 400;
		base_cost[2] = 200;
		required_buildings[Building.POWER_STATION] = 3;
	}
	
	public Laboratory(int level)
	{
		super(level);
		base_cost[0] = 200;
		base_cost[1] = 400;
		base_cost[2] = 200;
		required_buildings[Building.POWER_STATION] = 3;
	}
	
	public String generateHeader()
	{
		return "Исследовательская лаборатория " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Необходима для исследования новых технологий. Увеличение уровня повышает скорость исследований.<br>" + super.generateDescription(current_resources);
	}
}
