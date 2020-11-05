package adaptogame.core;

import java.io.IOException;
import java.util.ArrayList;

import adaptogame.core.technologies.Technology;
import adaptogame.core.units.Unit;
import adaptogame.core.units.fleet.LargeTransport;

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
		fleets = new ArrayList<>();
		current_planet_index = 0;
		active_research = NO_ACTIVE_RESEARCH;
		planet_changed = false;
		fleets.add(new Fleet(new Unit[] {new LargeTransport("Большой транспорт", 1)}, MissionCategory.LEAVE, new int[] {1, 1, 2}, new int[] {1, 1, 1}, 100, 25000, new int[] {0, 0, 0}));
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
	
	public int getFleetsSize()
	{
		return fleets.size();
	}
	
	public Fleet getFleet(int index)
	{
		return fleets.get(index);
	}
	
	public int getMaxFleetsAvailable()
	{
		return 1 + getTechs()[Technology.COMPUTER_TECHNOLOGY].getLevel();
	}
	
	public void addNewFleet(Unit[] units, MissionCategory mission, int[] coords_from, int[] coords_to, int speed_percent, int capacity_left, int[] resources_loaded)
	{
		fleets.add(new Fleet(units, mission, coords_from, coords_to, speed_percent, capacity_left, resources_loaded));
		planets[current_planet_index].departShips(units);
	}
	
	private Planet[] planets;
	private ArrayList<Fleet> fleets;
	private int current_planet_index;
	private Technology[] techs;
	private int active_research;
	private boolean planet_changed;
	public static final int NO_ACTIVE_RESEARCH = -1;
	public static final int MAX_FLEET_AMOUNT = 100;
}
