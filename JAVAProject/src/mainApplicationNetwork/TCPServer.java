package mainApplicationNetwork;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import checkIncheckOut.Check;
import company.CompanyController;
import company.CompanyModel;
import employees.NormalWorkerModel;
import timeTrackerCheckInOut.CheckInCheckOutModel;

/**
 * Class TCPServer
 * @author Marie DEGEN
 * @see TCPBuilderServer
 * @see TCPRWMessage
 */
public class TCPServer extends TCPBuilderServer implements Runnable 
{
	/**
	 * @param serialVersionUID for the serialization
	 * @param compCont CompanyController
	 * @param listWorkers key word for the server to understand what the client wants 
	 * @param checks key word for the server to understand what it has to do
	 */
	private static final long serialVersionUID = 1L;
	private CompanyController compCont;
	private String listWorkers = "ListWorkers";
	private String checks = "Checks";
	
	/**
	 * Constructor that initialize the company controller
	 * @param model CompanyModel
	 */
	public TCPServer(CompanyModel model)
	{
		this.compCont = new CompanyController(model);	
	}

	/**
	 * Run function
	 * Send the list of workers when the client ask for it
	 * Load new check for a given worker when a check is sent
	 * @exception Exception if something is wrong with the checks 
	 * @exception IOException if something went wrong in the network 
	 */
	@Override
	public void run()
	{
		try
		{
			//Connection and acceptation
			setSocket();
			s = ss.accept();
		
			while(true)
			{ 
				
				//Receive the string sent by the client
				ObjectInputStream ino = new ObjectInputStream(s.getInputStream());
				String st = (String)ino.readObject();
				st = st.trim();
				
				//if its ListWorkers then the server sent the current list of workers
				if(st.equals(listWorkers))
				{
					ObjectOutputStream os = new ObjectOutputStream(s.getOutputStream());
					os.writeObject(compCont.getListWorkerFromCompanyController());
					os.flush();
				}
				//if its Checks then it create a check for a given worker
				else if(st.equals(checks))
				{
					CheckInCheckOutModel check = (CheckInCheckOutModel)ino.readObject(); 
					NormalWorkerModel w = check.getWorker();
					
					//look for the worker into the list of workers
					for(NormalWorkerModel worker : compCont.getListWorkerFromCompanyController())
					{
						if(worker.getMail().equals(w.getMail()))
						{
							//if the check in list and the check out list have the same size then the check received is a check in
							if(worker.getListchecks().getListCheckIn().size() == worker.getListchecks().getListCheckOut().size())
							{
								Check checkIn = new Check(check.getHour());
								worker.addCheckIn(checkIn);
							}
							//if the check in list is bigger by one from the check out list that its a check out
							else if(worker.getListchecks().getListCheckIn().size() == worker.getListchecks().getListCheckOut().size() + 1)
							{
								Check checkOut = new Check(check.getHour());
								worker.addCheckOut(checkOut);
							}
							//otherwise its an error
							else
							{
								throw new Exception("The check isn't a check in or out, please recheck");
							}
							
						}
					}
					
				}
				else
				{
					throw new Exception("The server didn't receive the good key word");
				}
				
			}
		}
		catch(Exception  e)
		{
			e.printStackTrace();
		}

	}

}