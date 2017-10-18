package serializationDeserialization;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import company.CompanyModel;

/**
 * Class Serialization
 * @author Marie DEGEN
 * @see Deserialization
 */
public class Serialization
{
	/**
	 * Serialize the data into a file
	 * @param model CompanyModel
	 * @exception if it couldn't serialize
	 */
	public static void serialization(CompanyModel model, String FILE_NAME)
	{
		try
		{
    		FileOutputStream fos = new FileOutputStream(FILE_NAME);
    		ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(model);
			oos.close();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
}
