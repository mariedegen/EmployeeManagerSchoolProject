package employees;
import java.text.ParseException;
import java.time.LocalTime;
import checkIncheckOut.Check;
import checkIncheckOut.CheckInCheckOutModel;
import departements.*;
import hourWorksDays.WorkDays;

/**
  * Class NormalWorkerModel
  * @author Marie DEGEN
  * @see NormalWorkerController
  */
public class NormalWorkerModel extends Person
{
	/**Attributs of the NormalWorker class, all in private
	 * @param departement departement of the worker
	 * @param days days is the parameter that define the hours of work for the worker
	 * @param listchecks list of checkIn and list of checkOut of the worker
	 * @param serialVersionUID for the serialization
	 **/
	private StandardDepartementModel departement;
	private WorkDays days = new WorkDays();
	private CheckInCheckOutModel listchecks = new CheckInCheckOutModel(); 
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * @param name name of the manager
	 * @param firstName firstName of the manager
	 * @param mail mail of the manager 
	 * @throws ParseException 
	 */
	public NormalWorkerModel(String name, String firstName, String mail) throws ParseException
	{
		super(name, firstName, mail);
		LocalTime timebegin = LocalTime.of(9, 00);
		LocalTime timeEnd = LocalTime.of(18, 00);
		days.setHourDays(timebegin, timeEnd);
	}	

	/**
	 * Getter of the departement
	 * @return the StandardDepartement the manager is part of 
	 * @throws Exception when the worker doesn't have a departement
	 */
	public StandardDepartementModel getDepartement()
	{		
		return this.departement;
	}

	/**
	 * Setter of the departement
	 * @param departement departement the worker is a part of 
	 */
	public void setDepartement(StandardDepartementModel departement)
	{
		this.departement = departement;
	}
	
	/**
	 * Define the hours of work for the worker
	 * @param begin begin is the hour of beginning work for the worker 
	 * @param end end is the hour of ending work for the worker
	 */
	public void defineHourOfWork(LocalTime begin, LocalTime end) throws ParseException
	{
		days.setHourDays(begin, end);
	}
	
	/**
	 * toString check in
	 * @return a string with all the check in 
	 */
	public String toStringCheckIn()
	{
		String result = ""; 
		for(Check checksI : listchecks.getListCheckIn())
		{
			result += checksI.toString() +"\t";
		}
		return result; 
	}
	
	 
	/**
	 * toString check out
	 * @return a string with all the check out 
	 */
	public String toStringCheckOut()
	{
		String result = "";
		for(Check checksO : listchecks.getListCheckOut())
		{
			result += checksO.toString() +"\t";
		}
		
		return result; 
	}
	
	/**
	 * Getter of the listchecks
	 * @return the list of checks for the worker
	 */
	public CheckInCheckOutModel getListchecks()
	{
		return listchecks;
	}

	/**
	 * Add check in 
	 * @param checkI to add
	 */
	public void addCheckIn(Check checkI)
	{
		listchecks.addCheckIn(checkI);
	}
	
	/**
	 * Add check out
	 * @param checksO to add
	 */
	public void addCheckOut(Check checksO)
	{
		listchecks.addCheckOut(checksO);
	}
	
	/**
	 * remove check in 
	 * @param checkI to remove
	 */
	public void removeCheckIn(Check checkI)
	{
		listchecks.removeCheckIn(checkI);
	}
	
	/**
	 * Remove check out
	 * @param checksO to remove
	 */
	public void removeCheckOut(Check checksO)
	{
		listchecks.removeCheckOut(checksO);
	}
}
