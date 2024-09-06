package org.retal.offgame.core.technologies;

import org.retal.offgame.core.buildings.Building;

public class ImpulseEngine extends Technology 
{
	public ImpulseEngine(String name)
	{
		super(name);
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 2;
	}
	
	public ImpulseEngine(int level, String name)
	{
		super(level, name);
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 2;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Импульсный двигатель основывается на принципе отдачи. Дальнейшее развитие этих двигателей делает некоторые корабли быстрее, повышает скорость на 20% (относительно базовой скорости кораблей). Увеличивает дальность межпланетных ракет.<br>" + super.generateDescription(current_resources);
	}
}
