package timeTracker;
import java.io.Serializable;
import java.util.List;

import employees.NormalWorkerModel;
import timeTrackerCheckInOut.CheckInCheckOutModel;
import timeTrackerClientNetwork.TCPClient;

/**
 * TimeTrackerController 
 * @author Marie
 * @see TimeTrackerModel
 * @see TimeTrackerView
 */
public class TimeTrackerController  implements Serializable
{
	/**
	 * Attributes
	 * @param serialVersionUID for the serialization
	 * @param model TimeTrackerModel
	 * @param client TCPClient
	 * @param view TimeTrackerView
	 */
	private static final long serialVersionUID = 1L;
	private TimeTrackerModel model;
	private TCPClient client; 
	private TimeTrackerView view;
	
	/**
	 * Constructor
	 * @param model TimeTrackerModel
	 * @param view TimeTrackerView
	 * @param client TCPClient
	 */
	public TimeTrackerController(TimeTrackerModel model, TimeTrackerView view, TCPClient client)
	{
		this.model = model ;  
		this.view = view; 
		this.client = client;
	}
	
	/**
	 * Check, add the check to the queue 
	 * @param normalWorker associated to the check
	 */
	public void check(NormalWorkerModel normalWorker) 
    {
    	CheckInCheckOutModel check = new CheckInCheckOutModel(); 
		check.setWorker(normalWorker);
		model.getChecks().add(check);
    }
    
	/**
	 * Sychronize the list of workers with the server
	 * @exception if there is a problem with the synchronization
	 */
    public void synchronize()
    {
    	try
		{
			List<NormalWorkerModel> listw = client.receiveListWorker();
			view.updateListWorker(listw);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
    }
	
}
