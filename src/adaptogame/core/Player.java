package adaptogame.core;

import java.io.IOException;

import adaptogame.core.technologies.Technology;

public class Player 
{
	public Player() throws IOException
	{
		planets = new Planet[2];
		planets[0] = Planet.generateStartPlanet(this);
		for(int i = 1; i < planets.length; i++)
		{
			planets[i] = Planet.generatePlanet(this, i + 1);
		}
		techs = Technology.createList();
		current_planet_index = 0;
		active_research = NO_ACTIVE_RESEARCH;
		planet_changed = false;
	}
	
	public int getCurrentPlanetIndex()
	{
		return current_planet_index;
	}
	
	public Planet getCurrentPlanet()
	{
		return planets[current_planet_index];
	}
	
	public void setCurrentPlanet(int index)
	{
		current_planet_index = index;
		planet_changed = true;
	}
	
	public void planetChangeHandled()
	{
		planet_changed = false;
	}
	
	public boolean isPlanetChanged()
	{
		return planet_changed;
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
	private int current_planet_index;
	private Technology[] techs;
	private int active_research;
	private boolean planet_changed;
	public static final int NO_ACTIVE_RESEARCH = -1;
}
