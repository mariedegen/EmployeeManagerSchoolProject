package timeTrackerCheckInOut;
import java.io.Serializable;
import java.time.LocalDateTime;

import employees.NormalWorkerModel;

/**
 * Class ChecksQueue
 * @author Marie DEGEN
 * @see ChecksQueue
 */
public class CheckInCheckOutModel implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param hour LocalDateTime
	 * @param worker associated to the check
	 */
	private static final long serialVersionUID = 1L;
	private LocalDateTime hour; 
	private NormalWorkerModel worker; 
	
	/**
	 * Constructor that create a check rounded to the closest quarter
	 */
	public CheckInCheckOutModel()
	{
		this.hour = LocalDateTime.now().withSecond(0).withNano(0); 
		int fullQuarter = hour.getMinute() / 15;
		int minInQuarter = hour.getMinute() % 15;
		
		if(minInQuarter <= 7)
		{
			hour = hour.minusMinutes(minInQuarter);
		}
		else
		{
			hour = hour.plusMinutes((fullQuarter+1)*15 - (hour.getMinute()));
		}
	}

	/**
	 * Getter of the hour
	 * @return the hour
	 */
	public LocalDateTime getHour()
	{
		return hour;
	}

	/**
	 * Setter of the hour
	 * @param hour the hour to set
	 */
	public void setHour(LocalDateTime hour)
	{
		this.hour = hour;
	}

	/**
	 * Getter of the worker
	 * @return the worker
	 */
	public NormalWorkerModel getWorker()
	{
		return worker;
	}

	/**
	 * Setter of the worker
	 * @param worker the worker to set
	 */
	public void setWorker(NormalWorkerModel worker)
	{
		this.worker = worker;
	}


}
