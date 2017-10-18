package departements;
import java.io.Serializable;
import java.util.Observable;

/**
 * Abstrat Class Departement
 * @author Marie DEGEN
 * @see ManagementDepartementModel
 * @see StandardDepartementModel
 */

public abstract class Departement extends Observable implements Serializable 
{
	/**Attributs of the Departement abstract class, all in private
	 * @param name name of the departement
	 * @param IDDepartement IDDepartement of the departemet
	 * @param lastId lastId a counter
	 * @param serialVersionUID for the serialization
	 **/
	private int IDDepartement; 
	private String name;
	private static int lastId = 0;
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructor.
	 * @see ManagementDepartementModel 
	 * @see StandardDepartementModel
	 * @param name name of the departement
	 */
	public Departement(String name)
	{
		this.name = name; 
		this.IDDepartement = lastId++; 
	}
	
	/**
	 * Getter of the ID departement.
	 * @return the ID of the departement
	 */
	public int getIDDepartement()
	{
		return IDDepartement;
	}
	
	/**
	 * Getter of the name.
	 * @return the name of the departement
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Setter of the name.
	 * Change the name of departement by another one
	 * @param name name of the departement
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * toString function
	 * Return a string with all the information of the departement
	 * @return a string with the name
	 */
	public String toString()
	{
		return name; 
	}
}
