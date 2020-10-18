package offgame;

public class HyperspaceTechnology extends Technology 
{
	public HyperspaceTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 4000; 
		base_cost[2] = 2000;
	}
	
	public HyperspaceTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 4000; 
		base_cost[2] = 2000;
	}
	
	public String generateHeader()
	{
		return "��������������������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "���� ��������� 4-�� � 5-�� ��������� ����� ��������� ����������� ����� ����� ��������� � ����������� ���������.<br>" + super.generateDescription(current_resources);
	}
}
