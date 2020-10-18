package offgame;

public class IntergalacticalResearchNetwork extends Technology 
{ // ������� ����� ��������
	public IntergalacticalResearchNetwork()
	{
		super();
		base_cost[0] = 240000;
		base_cost[1] = 400000; 
		base_cost[2] = 160000;
	}
	
	public IntergalacticalResearchNetwork(int level)
	{
		super(level);
		base_cost[0] = 240000;
		base_cost[1] = 400000; 
		base_cost[2] = 160000;
	}
	
	public String generateHeader()
	{
		return "���������������� ����������������� ���� " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "��� ���� ������ ��������� ������� ������, ���������� � ����������������� ������������ ������ ������. ������ ����� ������� ��������� ������������ � ���� �������������� �����������, ��� ��������� �������� ������������.<br>" + super.generateDescription(current_resources);
	}
}
