package departements;
import java.util.List;

import company.CompanyModel;
import employees.ManagerModel;

/**Class ManagementDepartementModel
 * @see ManagementDepartementModel
 **/

public class ManagementDepartementController
{
	/**Attributs of the ManagementDepartement
	 * @param model, a CompanyModel 
	 **/
	private CompanyModel model;
	
	
	/**
	 * Constructor of a ManagementDepartementController
	 * @param model CompanyModel
	 */
	public ManagementDepartementController(CompanyModel model)
	{
		this.model = model; 
	}
	
	/**
	 * Create ManagementDepartement
	 * @param name of the management departement
	 * @throws Exception if two management departements have the same name
	 */
	public void createManagementDepartementController(String name) throws Exception
	{
		if(model.getListDepartement().equals(name))
		{
			throw new Exception("You can't have two departements with the same name");
		}
		@SuppressWarnings("unused")
		ManagementDepartementModel dep = new ManagementDepartementModel(name); 
	}
	
	/**
	 * Getter of the ID departement.
	 * @author Marie DEGEN
	 * @return the ID of the departement
	 */
	public int getIDDepartementController()
	{
		return model.getManagement().getIDDepartement();
	}
	
	/**
	 * Getter of the name.
	 * @return the name of the departement
	 */
	public String getNameController()
	{
		return model.getManagement().getName();
	}
	
	/**
	 * Setter of the name.
	 * Change the name of departement by another one
	 * @param name name of the departement
	 */
	public void setNameController(String newname)
	{
		model.getManagement().setName(newname);
	}

	/**
	 * Getter of the listManager.
	 * @return the listManager
	 */
	public List<ManagerModel> getListManagerController()
	{
		return model.getManagement().getListManager();
	}
	
	/**
	 * Delete a manager from the listManager
	 * @param manager that must be removed 
	 */
	public void deleteManagerFromDepartementController(ManagerModel manager)
	{
		model.getManagement().deleteManagerFromDepartement(manager);
	}
	
	/**
	 * Add a manager in the listManager
	 * @param manager that must be added 
	 */
	public void addManagerController(ManagerModel manager) throws Exception
	{
		model.getManagement().addManager(manager);
	}
	
	/**
	 * @return a string of the attributs from the management departement
	 */
	public String toStringControllerManagement()
	{
		return model.getManagement().toString();
	}
}
