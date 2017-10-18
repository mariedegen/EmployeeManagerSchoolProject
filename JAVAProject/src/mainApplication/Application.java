package mainApplication;
import java.time.LocalDateTime;
import java.time.LocalTime;

import checkIncheckOut.Check;
import company.CompanyModel;
import departements.ManagementDepartementModel;
import departements.StandardDepartementModel;
import employees.Boss;
import employees.ManagerModel;
import employees.NormalWorkerModel;
import mainApplicationNetwork.TCPServer;
import mainApplicationViews.Window;
import serializationDeserialization.Deserialization;

public class Application 
{	
	public static void main(String[] args) 
	{
		try
		{	
			CompanyModel company = new CompanyModel("TheBest");
			Boss boss = Boss.createBoss("DEGEN", "Marie", "marie.degen@thebest.fr");
			company.addBoss(boss);
			/*//Creation of Departement 
			StandardDepartementModel commercial = new StandardDepartementModel("Commercial");
			ManagementDepartementModel management = new ManagementDepartementModel("Management");
			StandardDepartementModel technique = new StandardDepartementModel("Technique");
			
			//Creation of Normal workers
			NormalWorkerModel w1 = new NormalWorkerModel("MARTIN","JeanMich","jeanmich.martin@thebest.fr");
			NormalWorkerModel w2 = new NormalWorkerModel("DUPONT","Jean","jean.dupont@thebest.fr");
			NormalWorkerModel w3 = new NormalWorkerModel("MICHEL","Louise","louise.miche@thebest.fr");
			NormalWorkerModel w4 = new NormalWorkerModel("DION","Céline","celine.dion@thbest.fr");
			
			//Creation of Manager 
			ManagerModel manager1 = new ManagerModel("BERTIGNAC", "Louis", "louis.bertignac@thebest.fr", management);
			ManagerModel manager2 = new ManagerModel("PERRIN", "Richard", "richard.perrin@thebest.fr", management);
			ManagerModel manager3 = new ManagerModel("THOMAS", "Vincent", "vincent.thomas@thebest.fr", management);
			

			
			//add manager and worker to the compagny 
			company.addManager(manager1);
			company.addManager(manager2);
			company.addManager(manager3);
			company.addWorker(w1);
			company.addWorker(w2);
			company.addWorker(w3);
			company.addWorker(w4);
			
			//add the departements to the company
			company.addDepartement(technique);
			company.addDepartement(commercial);
			company.setManagement(management);
			
			System.out.println("List of the manager of the compagny : " + company.printListManager() +"\n");
			
			
			//add manager and set the lead manager for commercial departement 
			commercial.addManager(manager1);
			commercial.addManager(manager3);
			commercial.setManagerLeadOfDepartement(manager1);
			
			//add manager and set the lead manager for technical departement 
			technique.addManager(manager2);
			technique.setManagerLeadOfDepartement(manager2);
			
			
			//Add worker 1 & 2 to commercial departement
			commercial.addWorker(w1);
			
			commercial.addWorker(w2);
			
			
			//Add worker 3 & 4 to technical departement
			technique.addWorker(w3);
			
			technique.addWorker(w4);
			
			System.out.println("Commercial departement is leaded by : " + commercial.getManagerOfDepartement()+"\n");
			System.out.println("Manager 2 is part of : " + manager2.getDepartement() +"\n");
			System.out.println("Technique departement is leaded by : " + technique.getManagerOfDepartement() +"\n");
			
			//print some of the results 
			System.out.println("");
			System.out.println("List of the worker in the compagny : " + company.printListNormalWorker() +"\n");
			System.out.println("List of the manager of the compagny : " + company.printListManager() +"\n");
			System.out.println("List of the departement of the compagny : " + company.printListDepartement() +"\n"); 
			
			System.out.println("-------------------------");
			System.out.println(commercial.getListWorker());
			System.out.println(management.getListManager());
			System.out.println(commercial.getManagerOfDepartement());
			System.out.println("-------------------------");
			
			//Fire worker 4 & manager 1 from the company 
			//company.deleteManager(manager2);
		
			//company.deleteWorker(w4);
			//company.deleteWorker(w3);
			
			
			//print some of the results 
			System.out.println("-------------------------");
			System.out.println("List of the worker in the compagny : " + company.printListNormalWorker() +"\n");
			System.out.println("List of the manager of the compagny : " + company.printListManager() +"\n");
			System.out.println("List of the departement of the compagny : " + company.printListDepartement() +"\n"); 
			
			System.out.println(commercial.getListWorker());
			System.out.println(management.getListManager());
			System.out.println(commercial.getManagerOfDepartement());
			System.out.println("-------------------------");
			
			System.out.println("List of worker for the technical departement : " + technique.getListWorker() +"\n");
			System.out.println("List of the manager in the management departement : " + management.getListManager() +"\n");
			System.out.println("List of the manager in lead in the technical departement : " + technique.getManagerOfDepartement() +"\n");
			
			//Delete a departement 
			//company.deleteDepartement(commercial);
			//System.out.println("List of the departement of the compagny : " + company.printListDepartement() +"\n"); 
			
			//Manager that doesn't lead any departement 
			System.out.println(manager3.isLead());
			
			//CheckInOut
			LocalTime time1 = LocalTime.of(3, 45);
			LocalTime time2 = LocalTime.of(6, 45);
			w1.defineHourOfWork(time1, time2);
			LocalDateTime time = LocalDateTime.now(); 

			Check in = new Check(time); 

			w1.addCheckIn(in);

			System.out.println(w1.toStringCheckIn());
			System.out.println(w1.toStringCheckOut());
			*/
			
			company = Deserialization.deserialization(company, "myData.ser");
			
			Window window = new Window(company);
			new Thread(new TCPServer(company)).start(); 
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
