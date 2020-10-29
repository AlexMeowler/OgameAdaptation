package adaptogame.core;

public enum CelestialBody 
{
	PLANET,
	DEBRIS,
	MOON;
	
	public String toString()
	{
		switch(this)
		{
			case PLANET:
				return "Планета";
			case DEBRIS:
				return "Поле обломков";
			case MOON:
				return "Луна";	
			default:
				return null;
		}
	}
	
	public CelestialBody fromString(String str)
	{
		if(str.equals("Планета"))
		{
			return PLANET;
		}
		else
		{
			if(str.equals("Поле обломков"))
			{
				return DEBRIS;
			}
			else
			{
				if(str.equals("Луна"))
				{
					return MOON;
				}
				else
				{
					return null;
				}
			}
		}
	}
}
