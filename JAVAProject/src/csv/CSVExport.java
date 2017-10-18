package csv;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import company.CompanyController;
import employees.ManagerModel;
import employees.NormalWorkerModel;

/**
 * Class CSVExport allow the user to export the date into a csv file
 * @author Marie DEGEN
 * @see CSVImport
 */
public class CSVExport
{
	/**
	 * CSVExport function allow the user to write data into a csv file
	 * @param compCont CompanyController
	 */
	public static void exportCSV(CompanyController compCont) throws FileNotFoundException
	{		
		String fichiercsv = "\"ID\";\"NAME\";\"FIRSTNAME\";\"MAIL\";\"DEPARTEMENTS\";\"ISMANAGER\";\"ISLEADMANAGER\"\n"; 
		PrintWriter pw = new PrintWriter(new File("exportcsv.csv"));
		
		//for each worker we write their data into a String
		for(NormalWorkerModel w : compCont.getListWorkerFromCompanyController())
		{
			if(compCont.getListManagerFromCompanyController().contains(w))
			{
				fichiercsv += w.getID() + ";" + w.getName() + ";" + w.getFirstName() + ";" + w.getMail() + ";" + w.getDepartement() + ";1;";
				
				ManagerModel m = (ManagerModel)w;
				
				if(m.isLead())
					fichiercsv += "1\n";
				else
					fichiercsv += "0\n";
			}
			else 
			{
				fichiercsv += w.getID() + ";" + w.getName() + ";" + w.getFirstName() + ";" + w.getMail() + ";" + w.getDepartement() + ";0;0\n";
			}

		}
		
		//write that string into the file
		pw.write(fichiercsv);
		pw.close();
	}

}
