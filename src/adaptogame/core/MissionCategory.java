package adaptogame.core;

public enum MissionCategory 
{
	ESPIONAGE,
	ATTACK,
	TRANSPORT,
	COLONIZE,
	LEAVE,
	DEFEND,
	EXPLORE;
	
	public String toString()
	{
		switch(this)
		{
			case ESPIONAGE:
				return "Шпионаж";
			case ATTACK:
				return "Атака";
			case TRANSPORT:
				return "Транспорт";
			case COLONIZE:
				return "Колонизировать";
			case LEAVE:
				return "Оставить";
			case DEFEND:
				return "Оборонять";
			case EXPLORE:
				return "Экспедиция";
			default:
				return null;
		}
	}
}
