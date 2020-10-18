package offgame;

public class IonTechnology extends Technology
{
	public IonTechnology()
	{
		super();
		base_cost[0] = 1000;
		base_cost[1] = 300; 
		base_cost[2] = 100;
	}
	
	public IonTechnology(int level)
	{
		super(level);
		base_cost[0] = 1000;
		base_cost[1] = 300; 
		base_cost[2] = 100;
	}
	
	public String generateHeader()
	{
		return "������ ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "�������� ������������ ��������� ��� �� ���������� �����. ��� ��������� �� �����-���� ������ ��� ������� �������� �����.<br>" + super.generateDescription(current_resources);
	}
}
