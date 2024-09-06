package adaptogame.core.technologies;

import adaptogame.core.buildings.Building;

public class ReactiveEngine extends Technology
{
	public ReactiveEngine(String name)
	{
		super(name);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 1;
	}
	
	public ReactiveEngine(int level, String name)
	{
		super(level, name);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 1;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Дальнейшее развитие этих двигателей делает некоторые корабли быстрее, однако каждый уровень повышает скорость лишь на 10% (относительно базовой скорости кораблей).<br>" + super.generateDescription(current_resources);
	}
}
