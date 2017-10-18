package serializationDeserialization;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import company.CompanyModel;

/**
 * Class Deserializarion
 * @author Marie DEGEN
 * @see Serialization
 */
public class Deserialization
{
	/**
	 * Deserialization function in static
	 * @param company CompanyModel
	 * @exception if out cant deserialize
	 */
	public static CompanyModel deserialization(CompanyModel company, String FILE_NAME)
	{
		try 
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			company = (CompanyModel)ois.readObject();
			
			if(company == null)
			{
				System.out.println("The company is empty");
			}
			
			ois.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return company;
	}

}
