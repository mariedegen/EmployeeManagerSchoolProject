package departements;
import java.util.ArrayList;
import java.util.List;

import employees.ManagerModel;
import employees.NormalWorkerModel;

/**Class StandardDepartementModel
 * @see StandardDepartementController
 * @see Departement
 **/

public class StandardDepartementModel extends Departement implements Comparable<StandardDepartementModel>
{
	/**Attributs of the ManagementDepartement
	 * @param listWorker listWorker that contains all the workers of the company
	 * @param manager manager of the departement
	 * @param serialVersionUID for the serialization
	 **/
	private List<NormalWorkerModel> listWorker;
	private ManagerModel manager; 
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param name name of the departement
	 * And the arrayList of listWorker is initalized
	 */
	public StandardDepartementModel(String name)
	{
		super (name); 		
		listWorker = new ArrayList<NormalWorkerModel>(); 
	}
	
	/**
	 * Getter of the ListWorker.
	 * @return the ListWorker
	 */
	public List<NormalWorkerModel> getListWorker()
	{
	
		return listWorker;
	}
	
	/**
	 * Set Manager of departement
	 * @param manager manager that must be in lead of the said departement 
	 * @exception when the manager isn't a worker of the departement
	 */
	public void setManagerLeadOfDepartement(ManagerModel manager) throws Exception
	{
		if(listWorker.contains(manager))
		{
			if(this.manager == null)
			{
				this.manager = manager; 
				manager.setLead(true);
			}
			else 
			{
				this.manager.setLead(false);
				this.manager = manager; 
				manager.setLead(true);
			}

		}
		else
		{
			throw new Exception("The manager can't lead the departement because he's not a worker in this departement");
		}
	}
	
	/**
	 * Delete a worker from the departement
	 * @param worker worker that must be removed
	 */
	public void deleteWorkerFromDepartement(NormalWorkerModel worker)
	{		
		if(worker.equals(manager))
		{
			this.manager = null;
		}
		
		listWorker.remove(worker);
	}
	
	/**
	 * Add a worker from the departement
	 * @param worker worker that must be added
	 */
	public void addWorker(NormalWorkerModel worker)
	{
		listWorker.add(worker);
		worker.setDepartement(this);
	}
	
	/**
	 * Add a manager from the departement
	 * @param manager manager that must be added
	 */
	public void addManager(ManagerModel manager)
	{
		listWorker.add(manager);
		manager.setDepartement(this);
	}

	/**
	 * Delete a manager from the departement
	 * @param manager manager that must be removed
	 */
	public void deleteManagerLeadOfDepartement()
	{
		this.manager.setLead(false);
		listWorker.remove(this.manager);
		this.manager = null; 
	}
	
	/**
	 * Get the manager of the departement
	 * @return the manager in lead for the said departement
	 */
	public ManagerModel getManagerOfDepartement()
	{
		return this.manager;
	}

	/**
	 * Compare two departement together
	 * @param dep a StandardDepartement
	 * @return 1 if the right object is bigger than the left one, 0 the objects are the same and -1 if the left object is smaller than the right one
	 */
	@Override
	public int compareTo(StandardDepartementModel dep)
	{
		return this.getName().compareTo(dep.getName());
	}
}

