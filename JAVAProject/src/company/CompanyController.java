package company;
import java.io.Serializable;
import java.util.List;

import checkIncheckOut.CheckInCheckOutController;
import departements.ManagementDepartementModel;
import departements.StandardDepartementModel;
import employees.Boss;
import employees.ManagerModel;
import employees.NormalWorkerModel;

public class CompanyController  implements Serializable
{
	private static final long serialVersionUID = 1L;
	private CompanyModel model; 
	
	public CompanyController(CompanyModel model)
	{
		this.model = model; 
	}
	
	/**
	 * Get the informations about the boss of the company
	 * @return the boss of the company 
	 * @exception if your company has no boss
	 */
	public Boss getbossfromcompany() throws Exception
	{		
		return model.getBoss();
	}
	
	/**
	 * Add manager
	 * @param manager manager to add to the list of managers
	 */
	public void addManagerToCompanyController(ManagerModel manager)
	{
		model.addManager(manager);
	}
	
	/**
	 * Add worker
	 * @param worker worker to add to the list of workers
	 */
	public void addWorkerToCompanyController(NormalWorkerModel worker)
	{
		model.addWorker(worker);
	}
	
	/**
	 * Add Departement
	 * @param departement departement to add to the list of departement
	 */
	public void addDepartementToCompanyController(StandardDepartementModel departement)
	{
		model.addDepartement(departement);
	}
	
	/**
	 * Delete manager
	 * @param name of the manager
	 * @param firstN of the manager
	 */
	public void deleteManagerFromCompanyController(String name, String firstN) throws Exception
	{
		ManagerModel m = null; 
		for(ManagerModel man : model.getListManager())
		{
			if(man.getName().equals(name) && man.getFirstName().equals(firstN))
			{
				m = man;
			}
		}
		model.deleteManager(m);
	}
	
	/**
	 * Delete worker
	 * @param name of the worker
	 * @param firstN of the worker
	 */
	public void deleteWorkerFromCompanyController(String name, String firstN) throws Exception
	{
		List<NormalWorkerModel> list = model.getListWorker();
		NormalWorkerModel workerToDelete = null;
		for(NormalWorkerModel w : list)
		{
			if(w.getName().equals(name) && w.getFirstName().equals(firstN))
			{
				workerToDelete = w;
			}
		}
		
		model.deleteWorker(workerToDelete);
		
		if(model.getListManager().contains(workerToDelete))
		{
			model.deleteManager((ManagerModel)workerToDelete);
		}
	}
	
	/**
	 * Delete departement
	 * @author Marie DEGEN
	 * @param name of the departement
	 */
	public void deleteDepartementFromCompanyController(String name)
	{
		StandardDepartementModel d = null;
		for(StandardDepartementModel dep : model.getListDepartement())
		{
			if(dep.getName().equals(name))
			{
				d = dep; 
			}
		}
		model.deleteDepartement(d);

	}

	/**
	 * Getter of the listWorker.
	 * @return the listWorker
	 */
	public List<NormalWorkerModel> getListWorkerFromCompanyController()
	{		
		return model.getListWorker();
	}

	/**
	 * Getter of the listDepartement.
	 * @return the listDepartement
	 */
	public List<StandardDepartementModel> getListDepartementFromCompanyController()
	{
		return model.getListDepartement();
	}

	/**
	 * Getter of the listManager.
	 * @return the listManager
	 */
	public List<ManagerModel> getListManagerFromCompanyController()
	{
		return model.getListManager();
	}
	
	/**
	 * Getter of the name of the company.
	 * @return the name company
	 */
	public String getNameOfCompanyController()
	{
		return model.getName();
	}

	/**
	 * Getter of the management departement.
	 * @return the management departement
	 */
	public ManagementDepartementModel getManagementFromCompanyController()
	{
		return model.getManagement();
	}

	/**
	 * Getter of the checks list for all the workers
	 * @return the listchecks
	 */
	public CheckInCheckOutController getListchecksFromCompanyController()
	{
		return model.getListchecks(); 
	}

}
