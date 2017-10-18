package employees;
import java.io.Serializable;
import java.util.Observable;

/**
 * Abstract Class Person
 * @author Marie
 * @see NormalWorkerModel
 * @see Boss
 *
 */
public abstract class Person extends Observable implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	/**Attributs of the Person abstract class, all in private
	 * @param name name of the persone
	 * @param firstName firstName of the person 
	 * @param ID ID of the person 
	 * @param mail mail of the person 
	 * @param lastId lastId a counter
	 **/
	private String name; 
	private String firstName; 
	private int ID; 
	private String mail; 
	private static int lastId = 0;
	
	
	/**
	 * Constructor.
	 * @param name name of the person
	 * @param firstName firstName of the person
	 * @param mail mail of the person 
	 */
	public Person(String name, String firstName, String mail)
	{
		ID = lastId ++; 
		this.name = name; 
		this.firstName = firstName; 
		this.mail = mail;
	}
	
	
	/**
	 * Getter of the name.
	 * @return the name of the person
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Setter of the name.
	 * @param name name of the person
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * Getter of the firstName.
	 * @return the firstName of the person
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * Setter of the firstName.
	 * @param firstName firstName of the person
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * Getter of the ID person.
	 * @return the ID of the person
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * Getter of the mail.
	 * @return the mail of the person
	 */
	public String getMail()
	{
		return mail;
	}
	
	/**
	 * Setter of the mail.
	 * @param mail mail of the person
	 */
	public void setMail(String mail)
	{
		this.mail = mail;
	} 
	
	
	/**
	 * toString function
	 * @return a string with the name, first name, mail of the person concerned
	 */
	public String toString()
	{
		return  name + " " + firstName + " " + "(" + mail + ")";
	}
	
}
