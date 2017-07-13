package avaj.launcher.simulator;

import java.util.List;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import avaj.launcher.simulator.exceptions.*;
import avaj.launcher.simulator.vehicles.Flyable;
import avaj.launcher.simulator.vehicles.AircraftFactory;

public class Simulator
{
	private static WeatherTower weatherTower;
	private static List<Flyable> flyables = new ArrayList<Flyable>();
	
	public static void main(String [] args)
	{
		if (args.length != 1)
		{
			System.out.println("The program expected exactly one argument, the description of the simulation as input file.");
			System.exit(1);
		}
		try
		{
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));
			String line = reader.readLine();
			if (line != null)
			{
				weatherTower = new WeatherTower();

				int simulations = Integer.parseInt(line);
				if (!isNumeric(line) || simulations < 0)
				{
					System.out.println("Invalid simulations count '" + line + "'");
					System.exit(2);
				}
				
				while ((line = reader.readLine()) != null)
				{
					if (!isNumeric(line.split(" ")[2]) || !isNumeric(line.split(" ")[3]) || !isNumeric(line.split(" ")[4]))
					{
						System.out.println("The longitude, latitude and height have to be numerics.");
						System.exit(3);
					}
					flyables.add(AircraftFactory.newAircaft(line.split(" ")[0], line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]), Integer.parseInt(line.split(" ")[4])));
				}
				reader.close();

				Logger.getLogger().open();
				for (Flyable flyable : flyables)
					flyable.registerTower(weatherTower);

				for (int i = 0 ; i < simulations; i++)
					weatherTower.changeWeather();
				Logger.getLogger().close();
			}
		}
		catch (EmptyFlyableNameException e)
		{
			System.out.println("The names of the flyables have to be not null.");
		}
		catch (HeightValueException e)
		{
			System.out.println("The height values of the flyables have to be set between 0 and 100.");
		}
		catch (LatitudeLongitudeValueException e)
		{
			System.out.println("The latitude and longitudes of the flyables have to be positives.");
		}
		catch (UnknownFlyableTypeException e)
		{
			System.out.println("The flyable type '" + e.getMessage() + "' is unknown.");
		}
		catch (UnknownWeatherException e)
		{
			System.out.println("The weather type '" + e.getMessage() + "' is unknown.");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Couldn't open the file '" + args[0] + "' or the log.");
		}
		catch (IOException e)
		{
			System.out.println("An error occured during the reading of the file '" + args[0] + "' or the log.");
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Each line of the file, except the first one, has to follow the scheme 'TYPE NAME LONGITUDE LATITUDE HEIGHT'.");
		}
		catch (Exception e)
		{
			System.out.println("An unknown error occured : '" + e.getMessage() + "'.");
		}
		finally
		{
			System.out.println("End of simulation");
		}
	}

	private static boolean isNumeric(String s)
	{
		for (int i = 0 ; i < s.length() ; i++)
			if (i == 0 && (s.charAt(i) == '+' || s.charAt(i) == '-'))
				continue;
			else if (s.charAt(i) < '0' || s.charAt(i) > '9')
				return false;
		return true;
	}
}