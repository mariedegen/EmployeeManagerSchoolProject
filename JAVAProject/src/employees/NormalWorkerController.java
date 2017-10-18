package employees;
import java.util.List;
import checkIncheckOut.Check;
import checkIncheckOut.CheckInCheckOutModel;
import company.CompanyModel;
import departements.StandardDepartementController;
import departements.StandardDepartementModel;

/**
 * Class NormalWorkerController 
 * @author Marie DEGEN
 * @see NormalWorkerModel
 */
public class NormalWorkerController
{
	/**
	 * Attributs
	 * @param model CompanyModel
	 * @param comtman ManagerController
	 */
	private CompanyModel model;
	private ManagerController contman;
	
	/**
	 * Constructor initialize model and contman
	 * @param model CompanyModel
	 */
	public NormalWorkerController(CompanyModel model)
	{
		this.model = model;
		contman = new ManagerController(model); 
	}
	
	/**
	 * Create worker
	 * @param name of the worker
	 * @param firstName of the worker
	 * @param mail of the worker
	 * @param nameDep of the worker
	 * @return
	 * @throws Exception if you have two workers with the same name
	 */
	public NormalWorkerModel createWorker(String name, String firstName, String mail, String nameDep) throws Exception
	{
		//looking if we already have a worker with this name
		for(NormalWorkerModel w : model.getListWorker())
		{
			if(w.getName().equals(name) && w.getFirstName().equals(firstName))
			{
				throw new Exception("You can't have two workers with the same name and first name");
			}
		}
		//otherwise create it
		NormalWorkerModel w = new NormalWorkerModel(name, firstName, mail);
		model.addWorker(w);
		
		StandardDepartementController departement = new StandardDepartementController(model);
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		//Looking for the departement
		StandardDepartementModel d = null; 
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(nameDep))
			{
				d = dep; 
				break; 
			}
		}
		// if doesnt exist we create it
		if(d == null)
		{
			d = departement.createDepartement(nameDep);
			model.addDepartement(d);
		}
		
		//set the departement to the worker & verify if he's a part of the dep before
		if(!d.getListWorker().contains(w))
		{
			d.addWorker(w);
		}
		w.setDepartement(d);

		return w; 
	}
	
	/**
	 * Get departement of worker
	 * @param name of the worker
	 * @param firstname of the worker
	 * @return the departement if found null otherwise
	 */
	public StandardDepartementModel getDepartement(String name, String firstname)
	{		

		for(NormalWorkerModel worker : model.getListWorker())
		{
			if(worker.getName().equals(name) && worker.getFirstName().equals(firstname))
			{
				return worker.getDepartement();
			}
		}
		return null;
	}

	/**
	 * Set departement of a manager
	 * @param nameW 
	 * @param firstnameW
	 * @param mailW
	 * @param nameDep
	 * @throws Exception
	 */
	public void setDepartement(String nameW, String firstnameW, String mailW, String nameDep) throws Exception
	{

		StandardDepartementController depart = new StandardDepartementController(model);
		List<StandardDepartementModel> list = model.getListDepartement(); 
		
		//Looking for the departement
		StandardDepartementModel d = null; 
		for(StandardDepartementModel dep : list)
		{
			if(dep.getName().equals(nameDep))
			{
				d = dep; 
				break; 
			}
		}
		// If the department doesn't exists yet, create it
		if(d == null)
		{
			d = depart.createDepartement(nameDep);
			model.addDepartement(d);
		}
		
		//Looking for the manager
		ManagerModel m = null; 
		
		for(ManagerModel man : model.getListManager())
		{
			if(man.getName().equals(nameW))
			{
				m = man; 
				m.setDepartement(d);
				break; 
				
			}
		}
		//If he doesnt exist we create it
		if(m == null)
		{
			m = contman.createManager(nameW, firstnameW, mailW, model.getManagement());
			model.addManager(m);
			m.setDepartement(d);
			d.addManager(m);
		}
		//if he does, verify he's a part of the departement
		else 
		{
			if(!d.getListWorker().contains(m))
			{
				d.addManager(m);
			}
		}
	}
	
	/**
	 * toString 
	 * @param name of the worker 
	 * @param firstname of the worker 
	 * @return the list of check in for a given worker
	 */
	public List<Check> toStringCheckIn(String name, String firstname)
	{
		for(NormalWorkerModel worker : model.getListWorker())
		{
			if(worker.getName().equals(name) && worker.getFirstName().equals(firstname))
			{
				return worker.getListchecks().getListCheckIn();
			}
		}
		return null;
	}
	
	/**
	 * toString 
	 * @param name of the worker 
	 * @param firstname of the worker 
	 * @return the list of check out for a given worker
	 */
	public List<Check> toStringCheckOut(String name, String firstname)
	{

		for(NormalWorkerModel worker : model.getListWorker())
		{
			if(worker.getName().equals(name) && worker.getFirstName().equals(firstname))
			{
				return worker.getListchecks().getListCheckOut();
			}
		}
		return null;
	}
	
	/**
	 * toString 
	 * @param name of the worker 
	 * @param firstname of the worker 
	 * @return the list of checks for a given worker
	 */
	public CheckInCheckOutModel getListchecks(String name, String firstname)
	{
		for(NormalWorkerModel worker : model.getListWorker())
		{
			if(worker.getName().equals(name) && worker.getFirstName().equals(firstname))
			{
				return worker.getListchecks();
			}
		}
		
		return null; 
	}
	
	/**
	 * Rename a worker
	 * @param name worker
	 * @param firstName worker
	 * @param newname worker
	 * @param newFname worker
	 * @param mail worker
	 */
	public void renameWorker(String name,String firstName, String newname, String newFname, String mail)
	{

		for(NormalWorkerModel w : model.getListWorker())
		{
			if(w.getName().equals(name) && w.getFirstName().equals(firstName))
			{
				w.setName(newname);
				w.setFirstName(newFname);
				w.setMail(mail);
			}
		}
		
	}
	
	

}
