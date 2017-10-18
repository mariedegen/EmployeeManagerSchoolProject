package horloge;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class Horloge
 * @author Marie DEGEN
 */
public class Horloge implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param date the today's date
	 * @paramm time initialize to 0 by default
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate date = LocalDate.now();
	private LocalTime time = LocalTime.of(0,0, 0);
	
	/**
	 * Getter of the Date 
	 * @return today's date
	 */
	public LocalDate getDate()
	{
		return date;
	}

	/**
	 * Getter of the time
	 * @return the time
	 */
	public LocalTime getTime()
	{
		return time;
	}
	
	/**
	 * Update the time to the current time
	 */
	public void updateTime()
	{
		time = LocalTime.now(); 
	}

	/**
	 * @return a string with the date and the time
	 */
	public String toString()
	{
		return date + " " + time.getHour() + ":"+ time.getMinute();
	}
	
}
