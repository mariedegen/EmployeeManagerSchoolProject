package csv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import company.CompanyController;
import departements.StandardDepartementModel;
import employees.ManagerController;
import employees.ManagerModel;
import employees.NormalWorkerController;
import employees.NormalWorkerModel;

/**
 * Class CSVImport allow the user to import a file with the format csv
 * @author Marie DEGEN
 * @see CSVExport
 */
public class CSVImport
{
	/**
	 * Attributs
	 * @param SEPARATOR is the separator defined
	 * @param PATH the path where the file is
	 * @param FILE_NAME the file name
	 */
	public final static String SEPARATOR = ";" ;
	public final static String PATH = "C:\\Users\\Marie\\Desktop\\";
	public final static String FILE_NAME = "exportcsv.csv"; 
	
	/**
	 * importCSV function that load a CSV file into the program
	 * @param managerCont ManagerController
	 * @param workerCont NormalWorkerController
	 * @param compCont CompanyController
	 */
	public static void importCSV(ManagerController managerCont, NormalWorkerController workerCont, CompanyController compCont) throws Exception
	{
		File file = new File(PATH + FILE_NAME); 

		Scanner scanner = new Scanner(file); 
		List <String[]> data = new ArrayList<String[]>();
		
		// We skip the first line, as it is the header.
		scanner.nextLine();
		
		while(scanner.hasNext())
		{
		    data.add(scanner.nextLine().split(";"));
		}
		
		/* Clear all the workers & managers. */
		compCont.getListWorkerFromCompanyController().clear();
		
		for(StandardDepartementModel sd : compCont.getListDepartementFromCompanyController())
		{
			sd.getListWorker().clear();
		}
		
		compCont.getListManagerFromCompanyController().clear();
		compCont.getListDepartementFromCompanyController().clear();
		
		/* Add the workers to the company. */
		for(String[] d : data)
		{
			String name = d[1];
			String firstName = d[2];
			String mail = d[3];
			String dep = d[4];
			String isManager = d[5];
			String isLead = d[6];
			
			if(isManager.equals("0"))
			{
				NormalWorkerModel w = workerCont.createWorker(name, firstName, mail, dep);
			}
			else
			{
				ManagerModel m = managerCont.createManager(name, firstName, mail, compCont.getManagementFromCompanyController());
				compCont.addManagerToCompanyController(m);
				workerCont.setDepartement(name, firstName, mail, dep);
				
				// If is lead manager of his department :
				if(isLead.equals("1"))
				{
					m.setLead(true);
					m.getDepartement().setManagerLeadOfDepartement(m);
				}
				else
				{
					m.setLead(false);
				}
			}
		}
		scanner.close();
		
	}
}

