package offgame;

public class ReactiveEngine extends Technology
{
	public ReactiveEngine()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
	}
	
	public ReactiveEngine(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
	}
	
	public String generateHeader()
	{
		return "���������� ��������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "���������� �������� ���� ���������� ������ ��������� ������� �������, ������ ������ ������� �������� �������� ���� �� 10% (������������ ������� �������� ��������).<br>" + super.generateDescription(current_resources);
	}
}
