package timeTracker;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

import employees.NormalWorkerModel;
import timeTrackerCheckInOut.CheckInCheckOutModel;

/**
 * Class TimeTrackerModel
 * @author Marie DEGEN
 * @see TimeTrackerController
 * @see TimeTrackerView
 */
public class TimeTrackerModel extends Observable  implements Serializable
{
	/**
	 * Attributs 
	 * @param serialVersionUID for the serialization
	 * @param checks a queue of checks
	 * @param listWorker a list of workers
	 */
	private static final long serialVersionUID = 1L;
	private Queue<CheckInCheckOutModel> checks; 
	private List<NormalWorkerModel> listWorker;
	
	/**
	 * Constructor that initialize the listWorker and the queue
	 */
	public TimeTrackerModel()
	{
		this.listWorker = new ArrayList<NormalWorkerModel>();
		this.setChecks(new ConcurrentLinkedQueue<CheckInCheckOutModel>());
	}
	
	/**
	 * Getter of the list of workers
	 * @return the listWorker
	 */
	public List<NormalWorkerModel> getListWorker()
	{
		return listWorker;
	}

	/**
	 * Setter of the worker list
	 * @param listWorker the listWorker to set
	 */
	public void setListWorker(List<NormalWorkerModel> listWorker)
	{
		this.listWorker = listWorker;
	}

	/**
	 * Getter of the queue
	 * @return the checks
	 */
	public Queue<CheckInCheckOutModel> getChecks()
	{
		return checks;
	}
	
	/**
	 * Setter of the queue
	 * @param checks the checks to set
	 */
	public void setChecks(Queue<CheckInCheckOutModel> checks)
	{
		this.checks = checks;
	}
}
