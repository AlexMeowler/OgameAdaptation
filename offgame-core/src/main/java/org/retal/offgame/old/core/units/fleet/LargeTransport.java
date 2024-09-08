package org.retal.offgame.old.core.units.fleet;

import org.retal.offgame.old.core.EngineCategory;
import org.retal.offgame.old.core.buildings.Building;
import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;

public class LargeTransport extends Unit
{
	public LargeTransport(String name) 
	{
		super(name);
		cost[0] = 6000;
		cost[1] = 6000;
		structure = 1200;
		shields = 25;
		attack_power = 5;
		cargo_volume = 25000;
		engine_type = EngineCategory.REACTIVE;
		speed = 7500;
		speed = 100000000;
		fuel_consumption = 50;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.REACTIVE_ENGINE] = 6;
	}
	
	public LargeTransport(String name, int amount) 
	{
		super(name, amount);
		cost[0] = 6000;
		cost[1] = 6000;
		structure = 1200;
		shields = 25;
		attack_power = 5;
		cargo_volume = 25000;
		engine_type = EngineCategory.REACTIVE;
		speed = 7500;
		speed = 100000000;
		fuel_consumption = 50;
		rapid_fire[SOLAR_SATELLITE] = 5;
		rapid_fire[SPY_PROBE] = 5;
		required_buildings[Building.SPACE_YARD] = 4;
		required_technologies[Technology.REACTIVE_ENGINE] = 6;
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Дальнейшее развитие малых транспортов позволило создать корабли, обладающие большей вместительностью и, благодаря более развитому двигателю, способными передвигаться быстрее, чем малый транспорт, до тех пор, пока на малых транспортах не устанавливаются импульсные двигатели 5-го уровня.<br>" + super.generateDescription(current_resources);
	}
}
