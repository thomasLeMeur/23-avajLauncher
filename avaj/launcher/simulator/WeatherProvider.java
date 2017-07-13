package avaj.launcher.simulator;

import java.util.Random;
import avaj.launcher.simulator.vehicles.Coordinates;

class WeatherProvider
{
	static private WeatherProvider weatherProvider = new WeatherProvider();
	static private String[] weather = {"SUN", "RAIN", "FOG", "SNOW"};
	
	private WeatherProvider()
	{
		weatherProvider = this;
	}
	
	public static WeatherProvider getProvider()
	{
		return weatherProvider;
	}
	
	public String getCurrentWeather(Coordinates coordinates)
	{
		int sun, rain, fog;
		if (coordinates.getHeight() < 20)
		{	//sun = 75%, rain/fog = 8/13%, snow = 4%
			sun = 74;
			rain = (coordinates.getLongitude() > coordinates.getLatitude()) ? sun + 13 : sun + 8;
			fog = 100 - 4;
		}
		else if (coordinates.getHeight() < 45)
		{	//sun = 40%, rain/fog = 20/30%, snow = 10%
			sun = 39;
			rain = (coordinates.getLongitude() > coordinates.getLatitude()) ? sun + 30 : sun + 20;
			fog = 100 - 10;
		}
		if (coordinates.getHeight() < 75)
		{	//sun = 22%, rain/fog = 22/27%, snow = 29%
			sun = 21;
			rain = (coordinates.getLongitude() > coordinates.getLatitude()) ? sun + 22 : sun + 27;
			fog = 100 - 29;
		}
		else
		{	//sun = 6%, rain/fog = 10/19%, snow = 65%
			sun = 5;
			rain = (coordinates.getLongitude() > coordinates.getLatitude()) ? sun + 10 : sun + 29;
			fog = 100 - 65;
		}
		Random rand = new Random();
		int ind = rand.nextInt(100);
		if (ind <= sun)
			ind = 0;
		else if (ind <= rain)
			ind = 1;
		else if (ind <= fog)
			ind = 2;
		else
			ind = 3;
		return weather[ind];
	}
}
