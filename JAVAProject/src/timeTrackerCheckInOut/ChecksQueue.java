package timeTrackerCheckInOut;
import java.util.Queue;

import timeTrackerClientNetwork.TCPClient;

/**
 * Class ChecksQueue
 * @author Marie DEGEN
 * @see CheckInCheckOutModel
 */

public class ChecksQueue implements Runnable
{
	/**
	 * Attributs
	 * @param queue of CheckInCheckOutModel
	 * @param client TCPClient
	 */
	private Queue<CheckInCheckOutModel> queue;
	private TCPClient client;
	
	public ChecksQueue(Queue<CheckInCheckOutModel> queue, TCPClient client)
	{
		this.queue = queue;
		this.client = client;
	}

	/**
	 * Run function that send the check to the serveur
	 * @exception if the check couldn't have been send
	 */
	@Override
	public void run()
	{
		while(true)
		{			
			try
			{
				if(!queue.isEmpty())
				{
					client.sendCheck(queue.element());
					queue.remove();
				}
				
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
	}
		
	} 
}
