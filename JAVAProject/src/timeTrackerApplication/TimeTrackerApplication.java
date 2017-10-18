package timeTrackerApplication;
import timeTracker.TimeTrackerModel;
import timeTracker.TimeTrackerView;
import timeTrackerCheckInOut.ChecksQueue;
import timeTrackerClientNetwork.TCPClient;

/**
 * Main class for the TimeTracker
 * @author Marie
 */
public class TimeTrackerApplication
{
	public static void main(String[] args)
	{
		TCPClient client =  new TCPClient(); 
		TimeTrackerModel model = new TimeTrackerModel();
		
		@SuppressWarnings("unused")
		TimeTrackerView view = new TimeTrackerView(model, client);
		
		new Thread(new ChecksQueue(model.getChecks(), client)).start(); 
	}

}
