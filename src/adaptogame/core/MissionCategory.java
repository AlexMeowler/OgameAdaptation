package adaptogame.core;

public enum MissionCategory 
{
	ESPIONAGE,
	ATTACK,
	TRANSPORT,
	COLONIZE,
	LEAVE,
	DEFEND, //usused
	EXPLORE,
	REFINE;
	
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
			case REFINE:
				return "Переработать";
			default:
				return null;
		}
	}
	
	public static MissionCategory fromString(String input)
	{
		if(input.equals("Шпионаж"))
		{
			return ESPIONAGE;
		}
		else
		{
			if(input.equals("Атака"))
			{
				return ATTACK;
			}
			else
			{
				if(input.equals("Транспорт"))
				{
					return TRANSPORT;
				}
				else
				{
					if(input.equals("Колонизировать"))
					{
						return COLONIZE;
					}
					else
					{
						if(input.equals("Оставить"))
						{
							return LEAVE;
						}
						else
						{
							if(input.equals("Оборонять"))
							{
								return DEFEND;
							}
							else
							{
								if(input.equals("Экспедиция"))
								{
									return EXPLORE;
								}
								else
								{
									if(input.equals("Переработать"))
									{
										return REFINE;
									}
									else
									{
										return null;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
