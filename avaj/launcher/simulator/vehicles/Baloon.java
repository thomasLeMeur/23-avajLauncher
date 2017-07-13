package avaj.launcher.simulator.vehicles;

import java.io.IOException;
import avaj.launcher.simulator.Logger;
import avaj.launcher.simulator.WeatherTower;
import avaj.launcher.simulator.exceptions.UnknownWeatherException;

class Baloon extends Aircraft implements Flyable
{
	private WeatherTower weatherTower;
	
	Baloon(String name, Coordinates coordinates)
	{
		super(name, coordinates);
	}
	
	public void updateConditions() throws UnknownWeatherException, IOException
	{
		String weather = this.weatherTower.getWeather(this.coordinates);
		if (weather.equals("SUN"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), Math.min(this.coordinates.getHeight() + 4, 100));
			Logger.getLogger().writeLog("Baloon#" + this.name + "(" + this.id + "): Let's enjoy the good weather and take some pics.");
		}
		else if (weather.equals("RAIN"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), Math.max(this.coordinates.getHeight() - 5, 0));
			Logger.getLogger().writeLog("Baloon#" + this.name + "(" + this.id + "): Damn you rain! You messed up my baloon.");
		}
		else if (weather.equals("FOG"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), Math.max(this.coordinates.getHeight() - 3, 0));
			Logger.getLogger().writeLog("Baloon#" + this.name + "(" + this.id + "): Baah, I don't know where I am.");
		}
		else if (weather.equals("SNOW"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), Math.max(this.coordinates.getHeight() - 15, 0));
			Logger.getLogger().writeLog("Baloon#" + this.name + "(" + this.id + "): It's snowing. We're gonna crash.");
		}
		else
			throw new UnknownWeatherException(weather);
		if (this.coordinates.getHeight() <= 0)
		{
			Logger.getLogger().writeLog("Baloon#" + this.name + "(" + this.id + ") landing.");
			Logger.getLogger().writeLog("Tower says: Baloon#" + this.name + "(" + this.id + ") unregistered from weather tower");
			this.weatherTower.unregister(this);
		}
	}
	
	public void registerTower(WeatherTower weatherTower) throws IOException
	{
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Logger.getLogger().writeLog("Tower says: Baloon#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
	
}
