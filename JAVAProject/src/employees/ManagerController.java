package employees;
import company.CompanyModel;
import departements.ManagementDepartementModel;

/**
 * Class ManagerController
 * @author Marie DEGEN
 * @see ManagerModel
 */
public class ManagerController
{
	/**
	 * Attributs
	 * @param model CompanyModel
	 */
	private CompanyModel model;
	
	/**
	 * Constructor, initialize the model
	 * @param model
	 */
	public ManagerController(CompanyModel model)
	{
		this.model = model; 
	}
	
	/**
	 * Create a manager
	 * @param name of the manager 
	 * @param firstName of the manager 
	 * @param mail of the manager 
	 * @param management departement
	 * @return the manager created
	 * @throws Exception if another manager with the same name was found
	 */
	public ManagerModel createManager(String name, String firstName, String mail,ManagementDepartementModel management) throws Exception
	{
		for(ManagerModel man : model.getListManager())
		{
			if(man.getName().equals(name) && man.getFirstName().equals(firstName))
			{
				throw new Exception("You can't have two managers with the same name and first name");
			}
		}
		ManagerModel manager = new ManagerModel(name, firstName, mail, management);
		return manager; 
	}
	
	/**
	 * Setter of the departementManagement
	 * @param departementManagement
	 * @param manager
	 */
	public void setDepartementManagement(ManagementDepartementModel departementManagement, ManagerModel manager)
	{
		for(ManagerModel man : model.getListManager())
		{
			if(man.equals(manager))
			{
				man.setDepartementManagement(departementManagement);
			}
		}
	}

	/**
	 * Getter of the departement management for a given manager
	 * @param manager
	 * @return the management departement of the manager if found, otherwise null
	 */
	public ManagementDepartementModel getManagementDepartement(ManagerModel manager)
	{
		for(ManagerModel man : model.getListManager())
		{
			if(man.equals(manager))
			{
				return man.getDepartementManagement(); 
			}
		}
		return null; 
	}
	
	/**
	 * Is lead Manager
	 * @param manager
	 * @return true if the manager is in lead of a departement, false if not, and if couldn't find the manager return false + message in the console
	 */
	public boolean isLead(ManagerModel manager)
	{
		for(ManagerModel man : model.getListManager())
		{
			if(man.equals(manager))
			{
				return man.isLead(); 
			}
		}
		System.out.println("Couldn't find the manager");
		return false;
	}

	/**
	 * Set if the manager is a lead or not
	 * @param isLead boolean
	 * @param manager
	 */
	public void setLead(boolean isLead, ManagerModel manager)
	{
		for(ManagerModel man : model.getListManager())
		{
			if(man.equals(manager))
			{
				man.setLead(isLead); 
			}
		}
	}
	
	/**
	 * Rename a manager
	 * @param name of the manager
	 * @param firstName of the manager
	 * @param newname of the manager
	 * @param newFname of the manager
	 * @param mail of the manager
	 */
	public void renameManager(String name,String firstName, String newname, String newFname, String mail)
	{
		for(ManagerModel man : model.getListManager())
		{
			if(man.getName().equals(name) && man.getFirstName().equals(firstName))
			{
				man.setName(newname);
				man.setFirstName(newFname);
				man.setMail(mail);
			}
		}
		
	}

}
