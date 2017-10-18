package company;
import java.io.Serializable;
import java.util.*;

import checkIncheckOut.CheckInCheckOutController;
import departements.*;
import employees.*;

/**
 * Class CompanyModel
 * @author Marie DEGEN
 * @see Departement
 * @see employees
 */
public class CompanyModel extends Observable implements Serializable
{	
	/**Attributs of the Company, all in private
	 * @param listWorker listWorker that contains all the workers of the company
	 * @param listManager listManager that contains all the managers of the company
	 * @param listDepartement listDepartemet that contains all the departement of the company
	 * @param name name of the company
	 * @param boss boss of the company
	 * @param management management departement
	 * @param listchecks list of the checks for all the workers
	 * @param serialVersionID for serialization
	 **/
	private String name; 
	private List<NormalWorkerModel> listWorker;
	private List<StandardDepartementModel> listDepartement;
	private List<ManagerModel> listManager; 
	private ManagementDepartementModel management; 
	private Boss boss; 
	private CheckInCheckOutController listchecks; 
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * @param name name of the compagny
	 * And the arrayList of listManager, listWorkers, listDepartement are initalized
	 */
	public CompanyModel(String name)
	{
		this.name = name; 
		this.listManager = new ArrayList<ManagerModel>(); 
		this.listWorker = new ArrayList<NormalWorkerModel>(); 
		this.listDepartement = new ArrayList<StandardDepartementModel>(); 
		this.management = new ManagementDepartementModel("Management"); 
	}
	
	/**
	 * Add the boss to the company
	 * @param boss boss of the company
	 */
	public void addBoss(Boss boss)
	{
		this.boss = boss;
	}
	
	/**
	 * Get the informations about the boss of the company
	 * @return the boss of the company 
	 * @exception if your company has no boss
	 */
	public Boss getBoss() throws Exception
	{		
		if(boss == null)
		{
			throw new Exception("Your company must have a boss");
		}
		else 
		{
			return boss; 
		}
	}
	
	/**
	 * Add manager
	 * @param manager manager to add to the list of managers
	 */
	public void addManager(ManagerModel manager)
	{
		listManager.add(manager);
		listWorker.add(manager);
		try
		{
			management.addManager(manager);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Add worker
	 * @param worker worker to add to the list of workers
	 */
	public void addWorker(NormalWorkerModel worker)
	{
		listWorker.add(worker);
	}
	
	/**
	 * Add Departement
	 * @param departement departement to add to the list of departement
	 */
	public void addDepartement(StandardDepartementModel departement)
	{
		listDepartement.add(departement); 
	}
	
	/**
	 * Delete manager
	 * @param manager manager to delete from the list of managers and from listWorker, listDepartement, managementDepartement he's a part of
	 */
	public void deleteManager(ManagerModel manager) throws Exception
	{
		listManager.remove(manager);
		listWorker.remove(manager);
		manager.getDepartementManagement().deleteManagerFromDepartement(manager);
		manager.getDepartement().deleteWorkerFromDepartement(manager);
	}
	
	/**
	 * Delete worker
	 * @param worker worker to delete from listWorker, departement he's a part of
	 */
	public void deleteWorker(NormalWorkerModel worker) throws Exception
	{
		listWorker.remove(worker);
		if(worker.getDepartement() != null)
		{
			worker.getDepartement().deleteWorkerFromDepartement(worker);
		}
	}
	
	/**
	 * Delete departement
	 * @param departement departement to delete from listDepartement
	 */
	public void deleteDepartement(StandardDepartementModel departement)
	{
		listDepartement.remove(departement);
	}

	/**
	 * Getter of the listWorker.
	 * @return the listWorker
	 */
	public List<NormalWorkerModel> getListWorker()
	{		
		if(listWorker == null || listWorker.size() ==0)
		{
			System.out.println("Congratulations you have no workers in your company");
		}
		
		return listWorker;
	}

	/**
	 * Getter of the listDepartement.
	 * @return the listDepartement
	 */
	public List<StandardDepartementModel> getListDepartement()
	{
		return listDepartement;
	}

	/**
	 * Getter of the listManager.
	 * @return the listManager
	 */
	public List<ManagerModel> getListManager()
	{
		return listManager;
	}
	
	/**
	 * Getter of the name of the company.
	 * @return the name company
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Getter of the management departement.
	 * @return the management departement
	 */
	public ManagementDepartementModel getManagement()
	{
		return management;
	}
	
	/**
	 * Setter of the name.
	 * @param name name the new name of the company
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Setter of the management departement.
	 * @param management management the new management departement
	 */
	public void setManagement(ManagementDepartementModel management)
	{
		this.management = management;
	}

	/**
	 * Setter of the listWorker.
	 * @param listWorker listWorker to set
	 */
	public void setListWorker(List<NormalWorkerModel> listWorker)
	{
		this.listWorker = listWorker;
	}

	/**
	 * Setter of the listDepartement.
	 * @param listDepartement listDepartement to set
	 */
	public void setListDepartement(List<StandardDepartementModel> listDepartement)
	{
		this.listDepartement = listDepartement;
	}

	/**
	 * Setter of the listManager.
	 * @param listManager listManager to set
	 */
	public void setListManager(List<ManagerModel> listManager)
	{
		this.listManager = listManager;
	}

	/**
	 * Print the list of managers
	 * @return a string with all the manager in the company
	 */
	public String printListManager()
	{
		String result = ""; 
		for(ManagerModel manager : listManager)
		{
			result += manager.toString();
		} 
		
		return result; 
			
	}
	
	/**
	 * Print the list of departements
	 * @return a string with all the departements in the company
	 */
	public String printListDepartement()
	{
		String result = ""; 
		for(StandardDepartementModel departement : listDepartement)
		{
			result += departement.toString();
		} 
		result +=management.toString();
		return result; 	
	}
	
	/**
	 * Print the list of workers
	 * @return a string with all the workers in the company
	 */
	public String printListNormalWorker()
	{
		String result = ""; 
		for(NormalWorkerModel worker : listWorker)
		{
			result += worker.toString();
		} 
		
		return result; 
	}

	/**
	 * Getter of the checks list
	 * @return the listchecks
	 */
	public CheckInCheckOutController getListchecks()
	{
		return listchecks;
	}

	/**
	 * Setter of the checks list
	 * @param listchecks the listchecks to set
	 */
	public void setListchecks(CheckInCheckOutController listchecks)
	{
		this.listchecks = listchecks;
	}
	
}
