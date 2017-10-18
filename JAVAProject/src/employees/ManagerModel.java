package employees;
import java.text.ParseException;

import departements.*;

/**
 * Class Manager
 * @author Marie DEGEN
 * @see Person
 * @see NormalWorkerModel
 */
public class ManagerModel extends NormalWorkerModel 
{
	/**
	 * Attributs
	 * @param departementManagement departement of the manager
	 * @param isLead boolean if the manager is lead of a departement or not
	 * @param serialVersionUID for the serializationS
	 **/
	private ManagementDepartementModel departementManagement; 
	private boolean isLead = false; 
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * @param name name of the manager
	 * @param firstName firstName of the manager
	 * @param mail mail of the manager 
	 * @param management departement of the company
	 * @throws ParseException 
	 */
	public ManagerModel(String name, String firstName, String mail,ManagementDepartementModel management) throws ParseException
	{
		super(name, firstName, mail);
		this.departementManagement = management; 
	}

	/**
	 * @return the departementManagement
	 */
	public ManagementDepartementModel getDepartementManagement()
	{
		return departementManagement;
	}

	/**
	 * @param departementManagement the departementManagement to set
	 */
	public void setDepartementManagement(ManagementDepartementModel departementManagement)
	{
		this.departementManagement = departementManagement;
	}

	/**
	 * @return the isLead
	 */
	public boolean isLead()
	{
		return isLead;
	}

	/**
	 * @param isLead the isLead to set
	 */
	public void setLead(boolean isLead)
	{
		this.isLead = isLead;
	}
	



}
