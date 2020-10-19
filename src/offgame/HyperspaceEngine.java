package offgame;

public class HyperspaceEngine extends Technology 
{
	public HyperspaceEngine()
	{
		super();
		base_cost[0] = 10000;
		base_cost[1] = 20000; 
		base_cost[2] = 6000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 3;
	}
	
	public HyperspaceEngine(int level)
	{
		super(level);
		base_cost[0] = 10000;
		base_cost[1] = 20000; 
		base_cost[2] = 6000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 3;
	}
	
	public String generateHeader()
	{
		return "√иперпространственный двигатель " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Ѕлагодар€ пространственно-временному изгибу в непосредственном окружении корабл€ пространство сжимаетс€, чем быстрее преодолеваютс€ далЄкие рассто€ни€. „ем выше развит гиперпростраственный привод, тем выше сжатие пространства, благодар€ чему с каждым уровнем скорость кораблей повышаетс€ на 30% (относительно базовой скорости кораблей).<br>" + super.generateDescription(current_resources);
	}
}
