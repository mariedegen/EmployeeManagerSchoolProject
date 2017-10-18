package employees;

/**
 * Class Boss
 * @author Marie
 * @see Person
 */
public class Boss extends Person 
{	
	/**Attributs of the NormalWorker class, all in private
	 * @param nbOfBoss nbOfBoss is fiwed to 1, because you can not have more that one boss
	 * @param serialVersionUID for the serialization
	 **/
	private static int nbOfBoss = 1; 
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor in private because we don't want people to create boss
	 * @param name name of the manager
	 * @param firstName firstName of the manager
	 * @param mail mail of the manager 
	 */
	private Boss(String name, String firstName, String mail)
	{
		super(name, firstName, mail);
	}
	
	/**
	 * Create Boss
	 * @param name name of the manager
	 * @param firstName firstName of the manager
	 * @param mail mail of the manager 
	 * @exception Exception if the number of boss created is bigger than 1
	 */
	public static Boss createBoss(String name, String firstName, String mail) throws Exception
	{
		if(nbOfBoss > 1)
		{
			throw new Exception("You can not have more than one boss in your compagny!");
		}
		nbOfBoss++; 
		return new Boss(name, firstName, mail);
	}
}
