package departements;
import java.util.List;

import company.CompanyModel;
import employees.ManagerController;
import employees.ManagerModel;
import employees.NormalWorkerModel;

/**Class StandardDepartementController
 * @see StandardDepartementModel
 * @see Departement
 **/

public class StandardDepartementController
{

	/**Attributs of the ManagementDepartement
	 * @param model, a CompanyModel 
	 * @param managerCont a ManagerController
	 **/
	private CompanyModel model;
	private ManagerController managerCont; 
	
	/**
	 * Contructor of a StandardDepartementController
	 * @param model a CompanyModel
	 * managerCont is initialize with the CompanyModel
	 */
	public StandardDepartementController(CompanyModel model)
	{
		this.model = model; 
		this.managerCont = new ManagerController(model);
	}
	
	/**
	 * Create a StandardDepartement
	 * @param name of the new departement
	 * @return the departement that has been created
	 * @exception if you already have a departement with the same name
	 */
	public StandardDepartementModel createDepartement(String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		try 
		{
			for(StandardDepartementModel dep : list)
			{
				if(dep.getName().equals(name))
				{
					throw new Exception("Your already have a departement with this name");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		StandardDepartementModel dep = new StandardDepartementModel(name); 
		return dep; 
	}
	
	/**
	 * Getter of the ID departement.
	 * @param name of the departement
	 * @return the ID of the departement if found with the name or -1 if not found
	 */
	public int getIDDepartement(String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				return dep.getIDDepartement();
			}
		}
		
		return -1;
	}
	
	/**
	 * Getter of the name.
	 * @param IDDepartement of the departement
	 * @return the name of the departement if found, otherwise null
	 */
	public String getName(int IDDepartement)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getIDDepartement() == IDDepartement)
			{
				return dep.getName();
			}
		}
		
		return null; 
	}
	
	/**
	 * Setter of the name.
	 * Change the name of departement by another one
	 * @param name current name of the departement
	 * @param newname of the departement
	 */
	public void setName(String name, String newname)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 

		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				dep.setName(newname);
			}
		}
	}
	
	/**
	 * toString function
	 * Return a string with all the information of the departement
	 * @param name of the departement
	 * @return a string with the name if found otherwise null
	 */
	public String toString(String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				return dep.toString();
			}
		}
		
		return null; 
	}
	
	/**
	 * Getter of the ListWorker.
	 * @param name of the departement
	 * @return the ListWorker or null otherwise
	 */
	public List<NormalWorkerModel> getListWorker(String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				return dep.getListWorker();
			}
		}
		
		return null; 
	}
	
	/**
	 * Set Manager of departement
	 * @param name of the departement
	 * @param nameManager
	 * @param fnmanager the first name of the manager
	 * @param mailManager
	 * @exception if the departement couldn't be found
	 */
	public void setManagerLeadOfDepartement(String nameDepartement, String nameManager, String fnmanager, String mailManager) throws Exception
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		//Looking for the departement
		StandardDepartementModel d = null; 
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(nameDepartement))
			{
				d = dep; 
				break; 
			}
		}
		if(d == null)
		{
			throw new Exception("Haven't found the departement");
		}
		
		
		List<ManagerModel> listman = model.getListManager();
		//Looking for the manager
		ManagerModel m = null; 
		
		for(ManagerModel man : listman)
		{
			if(man.getName().equals(nameManager))
			{
				m = man; 
				break; 
				
			}
		}
		//If he doesnt exist we create it
		if(m == null)
		{
			m = managerCont.createManager(nameManager, fnmanager, mailManager, model.getManagement());
			model.addManager(m);
			d.addManager(m);
			d.setManagerLeadOfDepartement(m);
		}
		//if he does verify he's a part of the departement and make him the lead manager
		else 
		{
			if(!d.getListWorker().contains(m))
			{
				d.addManager(m);
			}
			d.setManagerLeadOfDepartement(m);
		}
			
	}
	
	/**
	 * Delete a worker from the departement
	 * @param worker worker that must be removed
	 * @param name of the departement
	 */
	public void deleteWorkerFromDepartement(NormalWorkerModel worker, String name)
	{		
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				dep.deleteWorkerFromDepartement(worker);
			}
		}
	}
	
	/**
	 * Add a worker from the departement
	 * @param worker worker that must be added
	 * @param name of the departement
	 */
	public void addWorker(NormalWorkerModel worker, String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				dep.addWorker(worker);
			}
		}
	}
	
	/**
	 * Add a manager from the departement
	 * @param manager manager that must be added
	 * @param name of the departement
	 */
	public void addManager(ManagerModel manager, String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				dep.addManager(manager);
			}
		}
	}

	/**
	 * Delete a manager from the departement
	 * @param manager manager that must be removed
	 * @param name of the departement
	 */
	public void deleteManagerLeadOfDepartement(String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				dep.deleteManagerLeadOfDepartement();
			}
		}
	}
	
	/**
	 * Get the manager of the departement
	 * @param name of the departement
	 * @return the manager in lead for the said departement or null otherwise
	 */
	public ManagerModel getManagerOfDepartement(String name)
	{
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(name))
			{
				return dep.getManagerOfDepartement();
			}
		}
		
		return null; 
	}
}
