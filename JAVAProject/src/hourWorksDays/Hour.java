package hourWorksDays;
import java.io.Serializable;
import java.time.LocalTime;

/**
 * Class Hour
 * @author Marie DEGEN
 * @see WorkDays
 */

public class Hour implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param timeBegin
	 * @paramm timeEnd
	 */
	private static final long serialVersionUID = 1L;
	private LocalTime timeBegin;
	private LocalTime timeEnd;
	
	/**
	 * Constructor of Hour initialize the hour of begin and end to 0 
	 */
	public Hour()
	{
		this.timeBegin = LocalTime.of(0,0);
		this.timeEnd = LocalTime.of(0,0);
	}

	/**
	 * @return the timeBegin
	 */
	public LocalTime getTimeBegin()
	{
		return timeBegin;
	}

	/**
	 * @param timeBegin the timeBegin to set
	 */
	public void setTimeBegin(LocalTime timeBegin)
	{
		this.timeBegin = timeBegin;
	}

	/**
	 * @return the timeEnd
	 */
	public LocalTime getTimeEnd()
	{
		return timeEnd;
	}

	/**
	 * @param timeEnd the timeEnd to set
	 */
	public void setTimeEnd(LocalTime timeEnd)
	{
		this.timeEnd = timeEnd;
	}

	/**
	 * @return a string with the time (eg : 8:00 - 18:00)
	 */
	public String toString()
	{
		return timeBegin + " - " + timeEnd;
	}
		
}
