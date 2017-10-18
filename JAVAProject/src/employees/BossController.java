package employees;

/**
 * Class BossController
 * @author Marie
 * @see Boss
 * @see Person
 */
public class BossController
{	
	/**
	 * Create Boss
	 * @param name name of the manager
	 * @param firstName firstName of the manager
	 * @param mail mail of the manager 
	 * @exception Exception if the number of boss created is bigger than 1
	 */
	public Boss createBoss(String name, String firstName, String mail) throws Exception
	{
		return Boss.createBoss(name, firstName, mail);
	}
}
