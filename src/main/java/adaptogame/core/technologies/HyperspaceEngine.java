package adaptogame.core.technologies;

import adaptogame.core.buildings.Building;

public class HyperspaceEngine extends Technology 
{
	public HyperspaceEngine(String name)
	{
		super(name);
		base_cost[0] = 10000;
		base_cost[1] = 20000; 
		base_cost[2] = 6000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 3;
	}
	
	public HyperspaceEngine(int level, String name)
	{
		super(level, name);
		base_cost[0] = 10000;
		base_cost[1] = 20000; 
		base_cost[2] = 6000;
		required_buildings[Building.LABORATORY] = 7;
		required_technologies[Technology.HYPERSPACE_TECHNOLOGY] = 3;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Благодаря пространственно-временному изгибу в непосредственном окружении корабля пространство сжимается, чем быстрее преодолеваются далёкие расстояния. Чем выше развит гиперпростраственный привод, тем выше сжатие пространства, благодаря чему с каждым уровнем скорость кораблей повышается на 30% (относительно базовой скорости кораблей).<br>" + super.generateDescription(current_resources);
	}
}
