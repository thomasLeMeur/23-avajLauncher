package avaj.launcher.simulator;

import java.io.IOException;
import avaj.launcher.simulator.vehicles.Coordinates;
import avaj.launcher.simulator.exceptions.UnknownWeatherException;

public class WeatherTower extends Tower
{
	public String getWeather(Coordinates coordinates)
	{
		return WeatherProvider.getProvider().getCurrentWeather(coordinates);
	}
	
	void changeWeather() throws UnknownWeatherException, IOException
	{
		this.conditionsChanged();
	}
	
}
