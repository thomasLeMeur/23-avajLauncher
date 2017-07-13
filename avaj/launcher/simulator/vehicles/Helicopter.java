package avaj.launcher.simulator.vehicles;

import java.io.IOException;

import avaj.launcher.simulator.Logger;
import avaj.launcher.simulator.WeatherTower;
import avaj.launcher.simulator.exceptions.UnknownWeatherException;

class Helicopter extends Aircraft implements Flyable
{
	private WeatherTower weatherTower;
	
	Helicopter(String name, Coordinates coordinates)
	{
		super(name, coordinates);
	}
	
	public void updateConditions() throws UnknownWeatherException, IOException
	{
		String weather = this.weatherTower.getWeather(this.coordinates);
		if (weather.equals("SUN"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), Math.min(this.coordinates.getHeight() + 2, 100));
			Logger.getLogger().writeLog("Helicopter#" + this.name + "(" + this.id + "): This is hot.");
		}
		else if (weather.equals("RAIN"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
			Logger.getLogger().writeLog("Helicopter#" + this.name + "(" + this.id + "): I almost slipped from the cabin.");
		}
		else if (weather.equals("FOG"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
			Logger.getLogger().writeLog("Helicopter#" + this.name + "(" + this.id + "): Is there anyone?");
		}
		else if (weather.equals("SNOW"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), Math.max(this.coordinates.getHeight() - 12, 0));
			Logger.getLogger().writeLog("Helicopter#" + this.name + "(" + this.id + "): My rotor is going to freeze.");
		}
		else
			throw new UnknownWeatherException(weather);
		if (this.coordinates.getHeight() <= 0)
		{
			Logger.getLogger().writeLog("Helicopter#" + this.name + "(" + this.id + ") landing.");
			Logger.getLogger().writeLog("Tower says: Helicopter#" + this.name + "(" + this.id + ") unregistered from weather tower");
			this.weatherTower.unregister(this);
		}
	}
	
	public void registerTower(WeatherTower weatherTower) throws IOException
	{
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Logger.getLogger().writeLog("Tower says: Helicopter#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
}
