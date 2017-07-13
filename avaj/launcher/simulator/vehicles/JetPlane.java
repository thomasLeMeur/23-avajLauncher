package avaj.launcher.simulator.vehicles;

import java.io.IOException;

import avaj.launcher.simulator.Logger;
import avaj.launcher.simulator.WeatherTower;
import avaj.launcher.simulator.exceptions.UnknownWeatherException;

class JetPlane extends Aircraft implements Flyable
{
	private WeatherTower weatherTower;
	
	JetPlane(String name, Coordinates coordinates)
	{
		super(name, coordinates);
	}
	
	public void updateConditions() throws UnknownWeatherException, IOException
	{
		String weather = this.weatherTower.getWeather(this.coordinates);
		if (weather.equals("SUN"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, Math.min(this.coordinates.getHeight() + 2, 100));
			Logger.getLogger().writeLog("JetPlane#" + this.name + "(" + this.id + "): It's so cool dude!");
		}
		else if (weather.equals("RAIN"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
			Logger.getLogger().writeLog("JetPlane#" + this.name + "(" + this.id + "): It's raining. Better watch out for lightings.");
		}
		else if (weather.equals("FOG"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
			Logger.getLogger().writeLog("JetPlane#" + this.name + "(" + this.id + "): Something is giving me the london fog.");
		}
		else if (weather.equals("SNOW"))
		{
			this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), Math.max(this.coordinates.getHeight() - 7, 0));
			Logger.getLogger().writeLog("JetPlane#" + this.name + "(" + this.id + "): OMG! Winter is coming.");
		}
		else
			throw new UnknownWeatherException(weather);
		if (this.coordinates.getHeight() <= 0)
		{
			Logger.getLogger().writeLog("JetPlane#" + this.name + "(" + this.id + ") landing.");
			Logger.getLogger().writeLog("Tower says: JetPlane#" + this.name + "(" + this.id + ") unregistered from weather tower");
			this.weatherTower.unregister(this);
		}
	}
	
	public void registerTower(WeatherTower weatherTower) throws IOException
	{
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
		Logger.getLogger().writeLog("Tower says: JetPlane#" + this.name + "(" + this.id + ") registered to weather tower.");
	}
}
