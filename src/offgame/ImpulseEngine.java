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
		return "���������� ��������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "���������� ��������� ������������ �� �������� ������. ���������� �������� ���� ���������� ������ ��������� ������� �������, �������� �������� �� 20% (������������ ������� �������� ��������). ����������� ��������� ������������ �����.<br>" + super.generateDescription(current_resources);
	}
}
