package offgame;

public class ReactiveEngine extends Technology
{
	public ReactiveEngine()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 1;
	}
	
	public ReactiveEngine(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 1;
	}
	
	public String generateHeader()
	{
		return "–еактивный двигатель " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "ƒальнейшее развитие этих двигателей делает некоторые корабли быстрее, однако каждый уровень повышает скорость лишь на 10% (относительно базовой скорости кораблей).<br>" + super.generateDescription(current_resources);
	}
}
