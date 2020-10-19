package offgame;

public class NaniteFactory extends Building 
{
	public NaniteFactory()
	{
		super();
		base_cost[0] = 1000000;
		base_cost[1] = 500000;
		base_cost[2] = 100000;
		required_buildings[Building.ROBOT_FACTORY] = 10;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 10;
	}
	
	public NaniteFactory(int level)
	{
		super(level);
		base_cost[0] = 1000000;
		base_cost[1] = 500000;
		base_cost[2] = 100000;
		required_buildings[Building.ROBOT_FACTORY] = 10;
		required_technologies[Technology.COMPUTER_TECHNOLOGY] = 10;
	}
	
	public String generateHeader()
	{
		return "������� ������� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "������� ������� ���������� ����������� ������������������ ������� - �������. ������ - ��� ������, ������� � ��������� ��������� ���������������� ������� ���� �����������. ������ ������� � 2 ���� �������� ��������� ������, �������� � �������.<br>" + super.generateDescription(current_resources);
	}
}
