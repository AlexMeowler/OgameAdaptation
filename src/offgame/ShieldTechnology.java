package offgame;

public class ShieldTechnology extends Technology
{
	public ShieldTechnology()
	{
		super();
		base_cost[0] = 200;
		base_cost[1] = 600; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
	}
	
	public ShieldTechnology(int level)
	{
		super(level);
		base_cost[0] = 200;
		base_cost[1] = 600; 
		base_cost[2] = 0;
		required_buildings[Building.LABORATORY] = 6;
		required_technologies[Technology.ENERGY_TECHNOLOGY] = 3;
	}
	
	public String generateHeader()
	{
		return "������� ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "���������� ��������� ����� ����� ������������ �������� ��������������� �����, ��� ������ �� ����������� � ����������. ��������� �����, � ������ ������� ������������� ����� �������� � ������� ���������� �� 10%.<br>" + super.generateDescription(current_resources);
	}
}
