package offgame;

public class SpaceYard extends Building 
{
	public SpaceYard()
	{
		super();
		base_cost[0] = 400;
		base_cost[1] = 200;
		base_cost[2] = 100;
		required_buildings[Building.ROBOT_FACTORY] = 2;
	}
	
	public SpaceYard(int level)
	{
		super(level);
		base_cost[0] = 400;
		base_cost[1] = 200;
		base_cost[2] = 100;
		required_buildings[Building.ROBOT_FACTORY] = 2;
	}
	
	public String generateHeader()
	{
		return "Верфь " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "В строительной верфи производятся все виды кораблей и оборонительных сооружений. Увеличение уровня повышает скорость постройки кораблей и обороны.<br>" + super.generateDescription(current_resources);
	}
}
