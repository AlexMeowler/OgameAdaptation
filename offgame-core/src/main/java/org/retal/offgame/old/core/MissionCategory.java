package org.retal.offgame.old.core;

public enum MissionCategory 
{
	ESPIONAGE,
	ATTACK,
	TRANSPORT,
	COLONIZE,
	LEAVE,
	DEFEND, //unused
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
		switch(input)
		{
			case "Шпионаж":
				return ESPIONAGE;
			case "Атака":
				return ATTACK;
			case "Транспорт":
				return TRANSPORT;
			case "Колонизировать":
				return COLONIZE;
			case "Оставить":
				return LEAVE;
			case "Оборонять":
				return DEFEND;
			case "Экспедиция":
				return EXPLORE;
			case "Переработать":
				return REFINE;
			default:
				return null;
		}
	}
}
