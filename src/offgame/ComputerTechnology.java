package offgame;

public class ComputerTechnology extends Technology 
{
	public ComputerTechnology()
	{
		super();
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
	}
	
	public ComputerTechnology(int level)
	{
		super(level);
		base_cost[0] = 0;
		base_cost[1] = 400; 
		base_cost[2] = 600;
		required_buildings[Building.LABORATORY] = 1;
	}
	
	public String generateHeader()
	{
		return "������������ ���������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "� ����������� �������� ����������� ����� ����������� �� ������� ����������� ������. ������ ������� ������������ ���������� ����������� ������������ ���������� ������ �� ����.<br>" + super.generateDescription(current_resources);
	}
}
