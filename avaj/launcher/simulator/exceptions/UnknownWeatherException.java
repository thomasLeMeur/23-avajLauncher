package avaj.launcher.simulator.exceptions;

public class UnknownWeatherException extends Exception
{
	static final long serialVersionUID = 0;
	
	public UnknownWeatherException(String message)
	{
		super(message);
	}
}
