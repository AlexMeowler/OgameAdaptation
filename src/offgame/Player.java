package offgame;

import java.io.IOException;

public class Player 
{
	public Player() throws IOException
	{
		planets = new Planet[1];
		planets[0] = Planet.generateStartPlanet(this);
		techs = Technology.createList();
		current_planet = 0;
		active_research = NO_ACTIVE_RESEARCH;
	}
	
	public int getCurrentPlanetIndex()
	{
		return current_planet;
	}
	
	public Planet getCurrentPlanet()
	{
		return planets[current_planet];
	}
	
	public Planet getPlanet(int i)
	{
		return planets[i];
	}
	
	public int getPlanetAmount()
	{
		return planets.length;
	}
	
	public Planet[] getPlanets()
	{
		return planets;
	}
	
	public Technology[] getTechs()
	{
		return techs;
	}
	
	public int getActiveResearch() //код исследования, -1 если нет исследований
	{
		return active_research;
	}
	
	public void setActiveResearch(int code)
	{
		active_research = code;
	}
	
	private Planet[] planets;
	private int current_planet;
	private Technology[] techs;
	private int active_research;
	public static final int NO_ACTIVE_RESEARCH = -1;
}
