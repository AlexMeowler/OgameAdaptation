package offgame;

public class GravityTechnology extends Technology
{
	public GravityTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		base_cost[3] = 300000;
		base = 3;
	}
	
	public GravityTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		base_cost[3] = 300000;
		base = 3;
	}
	
	public String generateHeader()
	{
		return "�������������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "�������� - ��� �������, ������� �� �������� �� ������ �� ������� � ���������� ���� ����������. ���� ������� ������������������ ������ ���������� ����� ��������� ������������� �������������� ����, �������, ������� ������ ����, ��������� � ���� �����, ��������� ���� ����� ���������� ������� ��� ���� ����. ��������� �������� ������ � ��������� ����� ����������� ���a���� � �������.<br>" + super.generateDescription(current_resources);
	}
}
