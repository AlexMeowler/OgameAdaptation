package offgame;

public class Terraformer extends Building
{
	public Terraformer()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 50000;
		base_cost[2] = 100000;
	}
	
	public Terraformer(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 50000;
		base_cost[2] = 100000;
	}
	
	public String generateHeader()
	{
		return "Терраформер " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "Терраформер может преобразовывать огромные территории, делая их пригодными для застройки. Увеличивает количество полей на планете.<br>" + super.generateDescription(current_resources);
	}
}
