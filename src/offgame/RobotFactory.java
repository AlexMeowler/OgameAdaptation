package offgame;

public class RobotFactory extends Building
{
	public RobotFactory()
	{
		super();
		base_cost[0] = 400;
		base_cost[1] = 120;
		base_cost[2] = 200;
		required_buildings[Building.DEITERIUM_MINES] = 2;
	}
	
	public RobotFactory(int level)
	{
		super(level);
		base_cost[0] = 400;
		base_cost[1] = 120;
		base_cost[2] = 200;
		required_buildings[Building.DEITERIUM_MINES] = 2;
	}
	
	public String generateHeader()
	{
		return "‘абрика роботов " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "ѕредоставл€ет простую рабочую силу, которую можно примен€ть при строительстве планетарной инфраструктуры.  аждый уровень развити€ фабрики повышает скорость строительства зданий.<br>" + super.generateDescription(current_resources);
	}
}
