package checkIncheckOut;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Class check 
 * @author Marie DEGEN
 * @see CheckInCheckOut
 * @see CheckInCheckOutController
 */
public class Check  implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionID
	 * @param hour
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime date; 
	
	/**
	 * Constructor of check
	 * @param time, a LocalDateTime that correspond to the check in parameter
	 */
	public Check(LocalDateTime time)
	{
		this.date = time.withSecond(0).withNano(0); 
	}

	/**
	 * Getter of the date & hour
	 * @return date & hour
	 */
	public LocalDateTime getDateAndHour()
	{
		return date;
	}
	/**
	 * Getter of the hour
	 * @return hour
	 */
	public LocalTime getHour()
	{
		return date.toLocalTime();
	}
	
	/**
	 * Getter of the date
	 * @return date
	 */
	public LocalDate getDate()
	{
		return date.toLocalDate();
	}
	
	/**
	 * Setter of the date & hour
	 * @param date the date to set
	 */
	public void setHour(LocalDateTime date)
	{
		this.date = date;
	}
	
	/**
	 * toString function
	 * @return date in a string format
	 */
	public String toString()
	{
		return date.toString();
	}
	
}
