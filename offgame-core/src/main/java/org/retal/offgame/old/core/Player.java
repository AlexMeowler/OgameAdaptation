package org.retal.offgame.old.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.retal.offgame.old.core.technologies.Technology;
import org.retal.offgame.old.core.units.Unit;
import org.retal.offgame.old.ui.MainPanel;

public class Player 
{
	public Player(String name, int start_index) throws IOException
	{
		planets = new Planet[2];
		planets[0] = Planet.generateStartPlanet(this);
		for(int i = 0; i < planets.length; i++)
		{
			planets[i] = Planet.generatePlanet(this, start_index + i);
		}
		this.name = name;
		techs = Technology.createList();
		fleets = new ArrayList<>();
		current_planet_index = 0;
		active_research = NO_ACTIVE_RESEARCH;
		planet_changed = false;
	}
	
	public String getName()
	{
		return name;
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
		Planet planet = MainPanel.getPlanetByCoordinates(coords_from);
		planet.moveShips(units, Planet.FLEET_DEPART);
		for(int i = 0; i < resources_loaded.length; i++)
		{
			resources_loaded[i] = -resources_loaded[i];
		}
		MainPanel.getPlanetByCoordinates(coords_from).updateResources(resources_loaded);
	}
	
	public void processFleets()
	{
		int size = fleets.size();
		for(int i = 0; i < size; i++)
		{
			Fleet fleet = fleets.get(i);
			long time_remaining;
			if(!fleet.isGoingHome())
			{
				time_remaining = fleet.getToTargetDate().getTime() - new Date().getTime();
			}
			else
			{
				time_remaining = fleet.getFromTargetDate().getTime() - new Date().getTime();
			}
			if(time_remaining < 300)
			{
				boolean remove_required = false;
				Planet target_planet = MainPanel.getPlanetByCoordinates(fleet.getTarget());
				Unit[] ships = fleet.getShips();
				switch(fleet.getMission())
				{
					case ATTACK:
						break;
					case COLONIZE:
						break;
					case DEFEND:
						break;
					case ESPIONAGE:
						break;
					case EXPLORE:
						break;
					case LEAVE:
						target_planet.moveShips(ships, Planet.FLEET_LAND);
						target_planet.updateResources(fleet.getResourcesLoaded());
						remove_required = true;
						break;
					case REFINE:
						break;
					case TRANSPORT:
						remove_required = fleet.isGoingHome();
						if(!remove_required)
						{
							target_planet.updateResources(fleet.getResourcesLoaded());
						}
						else
						{
							ships = fleet.getShips();
							MainPanel.getPlanetByCoordinates(fleet.getBase()).moveShips(ships, Planet.FLEET_LAND);
						}
						break;
					default:
						remove_required = true;
						break;
				}
				fleet.goHome();
				if(remove_required)
				{
					fleets.remove(i);
				}
			}
		}
	}
	
	private String name;
	private Planet[] planets;
	private ArrayList<Fleet> fleets;
	private int current_planet_index;
	private Technology[] techs;
	private int active_research;
	private boolean planet_changed;
	public static final int NO_ACTIVE_RESEARCH = -1;
	public static final int MAX_FLEET_AMOUNT = 100;
}
