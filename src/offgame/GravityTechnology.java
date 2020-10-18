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
		return "√равитационна€ технологи€ " + getCurrentLevelString();
	}
	
	public String generateDescription(double[] current_resources)
	{
		return "√равитон - это частица, котора€ не обладает ни массой ни зар€дом и определ€ет силу прит€жени€. ѕутЄм запуска концентрированного зар€да гравитонов можно создавать искусственное гравитационное поле, которое, подобно чЄрной дыре, вт€гивает в себ€ массу, благодар€ чему можно уничтожать корабли или даже луны. ѕозвол€ет получить доступ к постройке самых совершенных корaблей и обороны.<br>" + super.generateDescription(current_resources);
	}
}
