package hourWorksDays;
import java.io.Serializable;
import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Class WorkDays
 * @author Marie DEGEN
 * @see Hour
 */
public class WorkDays implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param hour 
	 * @paramm map a map that take for key the DaysEnum and for value the hour
	 */
	private static final long serialVersionUID = 1L;
	private Hour hour = new Hour();  
	private Map<DaysEnum, Hour> map = new HashMap<DaysEnum, Hour>();
	
	/**
	 * Constructor WorkDays, each day is associated to an Object Hour
	 */
	public WorkDays()
	{
		map.put(DaysEnum.MONDAY, hour);
		map.put(DaysEnum.THUESDAY, hour);
		map.put(DaysEnum.WENESDAY, hour);
		map.put(DaysEnum.THURSDAY, hour);
		map.put(DaysEnum.FRIDAY, hour);
	}
	
	/**
	 * DaysEnum, enumeration of the week days
	 */
	public enum DaysEnum
	{
		MONDAY,
		THUESDAY,
		WENESDAY, 
		THURSDAY,
		FRIDAY;
	}
	
	
	/**
	 * Getter for the current day of the week 
	 * @return the current day if found in the enum or null otherwise
	 */
	public static DaysEnum getCurrentDay()
	{
		LocalDate date = LocalDate.now();
		DayOfWeek day = date.getDayOfWeek();
		
		switch(day.getValue())
		{
			case 1:
				return DaysEnum.MONDAY;
			
		}
		
		return null;
	}
	
	/**
	 * Set the hour for each day
	 * @param workHourBegin
	 * @param workHourEnd
	 * @throws ParseException if the user enter a wrong format for the hour
	 */
	public void setHourDays(LocalTime workHourBegin, LocalTime workHourEnd) throws ParseException
	{
		for(DaysEnum day : DaysEnum.values())
		{
			hour.setTimeBegin(workHourBegin);
			hour.setTimeEnd(workHourEnd);
			map.put(day, hour);
		}
	}
	
	/**
	 * Print for each day, their hour of work
	 */
	public void printHourDays()
	{
		for(DaysEnum days : DaysEnum.values())
		{
			System.out.println(days + " : " + map.get(days).getTimeBegin() + " - " +map.get(days).getTimeEnd());

		}
	}
	
	/**
	 * Get the work hours for the given day
	 * @param day of the enumeration 
	 * @return the work hours
	 */
	public Hour getHoursForDay(DaysEnum day)
	{
		return map.get(day);
	}
}
