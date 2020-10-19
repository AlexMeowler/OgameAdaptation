package offgame;

public class Metallurgy extends Technology
{
	public Metallurgy()
	{
		super();
		base_cost[0] = 1000;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 2;
	}
	
	public Metallurgy(int level)
	{
		super(level);
		base_cost[0] = 1000;
		base_cost[1] = 0; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 2;
	}
	
	public String generateHeader()
	{
		return "����������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "����������� ������ �������� ����� ����������� �������� � �������������� ����������, ������� �� ���������. ������ ������� ��������� ��������� �������� � ������� �� 10%.<br>" + super.generateDescription(current_resources);
	}
}
