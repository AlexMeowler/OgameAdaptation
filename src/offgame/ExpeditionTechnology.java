package offgame;

public class ExpeditionTechnology extends Technology 
{
	public ExpeditionTechnology()
	{
		super();
		base_cost[0] = 4000;
		base_cost[1] = 8000; 
		base_cost[2] = 4000;
		base = 3;
	}
	
	public ExpeditionTechnology(int level)
	{
		super(level);
		base_cost[0] = 4000;
		base_cost[1] = 8000; 
		base_cost[2] = 4000;
		base = 3;
	}
	
	public String generateHeader()
	{
		return "�������������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "��������� �������� ���������� � ������������ ����. ������ ������� ��������� �������������� ������� ���������� ������������� ����������.<br>" + super.generateDescription(current_resources);
	}
}
