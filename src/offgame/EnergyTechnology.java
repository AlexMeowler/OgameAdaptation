package offgame;

public class EnergyTechnology extends Technology 
{
	public EnergyTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 800; 
		base_cost[2] = 400;
	}
	
	public EnergyTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 800; 
		base_cost[2] = 400;
	}
	
	public String generateHeader()
	{
		return "�������������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "��������� ���������� ������ ������� ���������� ��� ������ ����� ����������. ��� �� ������ ������� ����������� ��������� ������� �� ������������ ���������������.<br>" + super.generateDescription(current_resources);
	}
}
