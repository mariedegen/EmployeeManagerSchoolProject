package checkIncheckOut;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Class CheckInCheckOut
 * @author Marie DEGEN
 * @see Check
 * @see CheckInCheckOutController
 */

public class CheckInCheckOutModel extends Observable  implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionID
	 * @param list of checkIn
	 * @param list of checkOut
	 */
	private static final long serialVersionUID = 1L;
	private List<Check> listCheckIn;
	private List<Check> listCheckOut; 
	
	/**
	 * Constructor of CheckInCheckOutModel
	 * intialization of the two lists
	 */
	public CheckInCheckOutModel()
	{
		this.listCheckIn = new ArrayList<Check>(); 
		this.listCheckOut = new ArrayList<Check>(); 
	}

	/**
	 * Getter of the checkIn list
	 * @return the checkIn list
	 */
	public List<Check> getListCheckIn()
	{
		return listCheckIn;
	}

	/**
	 * Setter of the checkIn list
	 * @param listCheckIn the listCheckIn to set
	 */
	public void setListCheckIn(List<Check> listCheckIn)
	{
		this.listCheckIn = listCheckIn;
	}

	/**
	 * Getter of the checkOut list
	 * @return the checkOut list to set
	 */
	public List<Check> getListCheckOut()
	{
		return listCheckOut;
	}

	/**
	 * Setter of the checkOut list
	 * @param listCheckOut the listCheckOut to set
	 */
	public void setListCheckOut(List<Check> listCheckOut)
	{
		this.listCheckOut = listCheckOut;
	}
	
	/**
	 * Add check in to the checkIn list
	 * @param checkin is the check in to add
	 */
	public void addCheckIn(Check checkin)
	{
		listCheckIn.add(checkin);
	}
	
	/**
	 * Remove check in to the checkIn list
	 * @param checkin to remove
	 */
	public void removeCheckIn(Check checkin)
	{
		listCheckIn.remove(checkin);
	}
	
	/**
	 * Add check out to the checkOut list
	 * @param checkout to add
	 */
	public void addCheckOut(Check checkout)
	{
		listCheckOut.add(checkout);
		
	}
	
	/**
	 * Remove check out to the checkOut list
	 * @param checkout to remove
	 */
	public void removeCheckOut(Check checkout)
	{
		listCheckOut.remove(checkout);
	}
	
	
}
