package offgame;

public class ImpulseEngine extends Technology 
{
	public ImpulseEngine()
	{
		super();
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 600;
	}
	
	public ImpulseEngine(int level)
	{
		super(level);
		base_cost[0] = 2000;
		base_cost[1] = 4000; 
		base_cost[2] = 600;
	}
	
	public String generateHeader()
	{
		return "Импульсный двигатель " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Импульсный двигатель основывается на принципе отдачи. Дальнейшее развитие этих двигателей делает некоторые корабли быстрее, повышает скорость на 20% (относительно базовой скорости кораблей). Увеличивает дальность межпланетных ракет.<br>" + super.generateDescription(current_resources);
	}
}
