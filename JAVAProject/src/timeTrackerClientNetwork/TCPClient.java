package timeTrackerClientNetwork;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import employees.NormalWorkerModel;
import timeTrackerCheckInOut.CheckInCheckOutModel;

/**
 * Class TCPBuilderClient
 * @author Marie DEGEN
 * @see TCPBuilderClient
 */

public class TCPClient extends TCPBuilderClient implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor that set the connexion with the serveur
	 * @exception if something udring the connexion went wrong
	 */
	public TCPClient()
	{
		try
		{
			setSocket();
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Receive the list of workers from the server
	 * @return the workers's list if everything went fine, null otherwise
	 * @throws Exception if the list of workers is empty
	 */
	public List<NormalWorkerModel> receiveListWorker() throws Exception
	{
		try
		{
		
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			out.writeObject((String)"ListWorkers\n");
			out.flush();
			
			//receive the workers list 
			ObjectInputStream in = new ObjectInputStream(s.getInputStream());
			
			List<NormalWorkerModel> listworker = (ArrayList<NormalWorkerModel>) in.readObject();
			
			if(listworker == null)
			{
				throw new Exception("The list of workers is empty");
			}
			
			return listworker; 
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null; 
		}
	}
	
	/**
	 * Send a check to the server
	 * @param check to send
	 * @exception if the check could've not been sent correctly
	 */
	public void sendCheck(CheckInCheckOutModel check) 
	{
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
			
			out.writeObject((String)"Checks\n");
			out.writeObject(check);
			
			out.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}