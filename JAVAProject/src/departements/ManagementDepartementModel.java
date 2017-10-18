package departements;
import java.util.*;

import employees.ManagerModel;

/**Class ManagementDepartementModel
 * @see Departement
 * @see StandardDepartementModel
 * @see ManagementDepartementController
 **/

public class ManagementDepartementModel extends Departement
{
	/**Attributs of the ManagementDepartement, all in private
	 * @param listManager listManager that contains all the manager of the company
	 * @param serialVersionUID for the serialization
	 **/
	private static List<ManagerModel> listManager;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * @param name name of the departement
	 * And the arrayList of listManager is initalized
	 */
	public ManagementDepartementModel(String name)
	{
		super(name);
		listManager = new ArrayList<ManagerModel>(); 
	}

	/**
	 * Getter of the listManager.
	 * @return the listManager
	 */
	public List<ManagerModel> getListManager()
	{
		if(listManager == null || listManager.size() == 0)
		{
			System.out.println("You don't have any manager for the moment in this departement");
		}
		
		return listManager;
	}
	
	/**
	 * Delete a manager from the listManager
	 * @param manager that must be removed 
	 */
	public void deleteManagerFromDepartement(ManagerModel manager)
	{
		listManager.remove(manager);
		
		if(listManager == null || listManager.size() == 0)
		{
			System.out.println("Your departement doesn't have a manager anymore in this departement");
		}
	}
	
	/**
	 * Add a manager in the listManager
	 * @param manager that must be added 
	 */
	public void addManager(ManagerModel manager) throws Exception
	{
		if(manager == null)
		{
			throw new Exception ("Your manager is null");
		}
		else 
		{
			listManager.add(manager); 
		}
	}
	
}
