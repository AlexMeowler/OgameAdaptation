package offgame;

public class Laboratory extends Building 
{
	public Laboratory()
	{
		super();
		base_cost[0] = 200;
		base_cost[1] = 400;
		base_cost[2] = 200;
		required_buildings[Building.POWER_STATION] = 3;
	}
	
	public Laboratory(int level)
	{
		super(level);
		base_cost[0] = 200;
		base_cost[1] = 400;
		base_cost[2] = 200;
		required_buildings[Building.POWER_STATION] = 3;
	}
	
	public String generateHeader()
	{
		return "����������������� ����������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "���������� ��� ������������ ����� ����������. ���������� ������ �������� �������� ������������.<br>" + super.generateDescription(current_resources);
	}
}
