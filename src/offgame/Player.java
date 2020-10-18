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
	}
	
	public int getCurrentPlanet()
	{
		return current_planet;
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
	
	private Planet[] planets;
	private int current_planet;
	private Technology[] techs;
}
