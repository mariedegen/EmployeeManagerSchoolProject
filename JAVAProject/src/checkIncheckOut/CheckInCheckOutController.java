package checkIncheckOut;
import java.io.Serializable;
import java.util.List;

/**
 * Class CheckInCheckOutController
 * @author Marie DEGEN
 * @see Check
 * @see CheckInCheckOutModel
 */
public class CheckInCheckOutController  implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionID
	 * @param CheckInCheckOutModel
	 */
	private static final long serialVersionUID = 1L;
	private CheckInCheckOutModel model; 
	
	/**
	 * Constructor of CheckInCheckOutController
	 * @param CheckInCheckOutModel
	 */
	public CheckInCheckOutController(CheckInCheckOutModel model)
	{
		this.model = model;
	}
	
	/**
	 * Getter of the checkIn list
	 * @return the checkIn list
	 */
	public List<Check> getListCheckInController()
	{
		return model.getListCheckIn();
	}

	/**
	 * Setter of the checkIn list
	 *@param listCheckIn is the list of checkIn to set
	 */
	public void setListCheckInController(List<Check> listCheckIn)
	{
		model.setListCheckIn(listCheckIn);
	}

	/**
	 * Getter of the checkOut list
	 * @return the checkOut list
	 */
	public List<Check> getListCheckOutController()
	{
		return model.getListCheckOut();
	}

	/**
	 * Setter of the checkOut list
	 * @param listCheckOut the checkOut list to set
	 */
	public void setListCheckOutController(List<Check> listCheckOut)
	{
		model.setListCheckOut(listCheckOut);
	}
	
	/**
	 * Add check in to the checkIn list
	 * @param checkin to add
	 */
	public void addCheckInController(Check checkin)
	{
		model.addCheckIn(checkin);
	}
	
	/**
	 * Remove check in to the checkIn list
	 * @param checkin to remove
	 */
	public void removeCheckInController(Check checkin)
	{
		model.removeCheckIn(checkin);
	}
	
	/**
	 * Add check out to the checkOut list
	 * @param checkout to add
	 */
	public void addCheckOutController(Check checkout)
	{
		model.addCheckOut(checkout);
	}
	
	/**
	 * Remove check out to the checkOut list
	 * @param checkout to remove
	 */
	public void removeCheckOutController(Check checkout)
	{
		model.removeCheckOut(checkout);
	}
	
}
