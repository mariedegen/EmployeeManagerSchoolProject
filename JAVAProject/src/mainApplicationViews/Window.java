package mainApplicationViews;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import company.CompanyController;
import company.CompanyModel;
import csv.CSVExport;
import csv.CSVImport;
import departements.ManagementDepartementController;
import departements.StandardDepartementController;
import employees.BossController;
import employees.ManagerController;
import employees.NormalWorkerController;
import horloge.Horloge;
import serializationDeserialization.Serialization;

/**
 * Main View of the application
 * @author Marie DEGEN
 */
public class Window extends JFrame
{
	/**
	 * Attributs (1)
	 * @param serialVersionUID for the serialization
	 * @param model CompanyModel 
	 * @param compCont CompanyController
	 * @param workerCont NormalWorkerController
	 * @param bosscont BossController
	 * @param standardCont StandardDepartementController
	 * @param managCont ManagementDepartementController
	 * @param managerCont ManagerController
	 */	
	private static final long serialVersionUID = 1L;
	private BossController bosscont = new BossController(); 
	private CompanyModel modelComp;
	private CompanyController compCont;
	private StandardDepartementController standardCont; 
	private ManagementDepartementController managCont;
	private NormalWorkerController workerCont;
	private ManagerController managerCont; 
	
	/**
	 * Attributs (2)
	 * @param menuBar JMenuBar
	 * @param staffDep JMenu
	 * @paral managDep JMenu
	 * @param checkincheckout JMenu
	 * @param day JMenu
	 * @param workers JMenu
	 * @param todayState JMenu
	 * @param csv JMenu
	 */
	//Creation of the menu and the different elements of the menu
	private JMenuBar menuBar = new JMenuBar(); 
	private JMenu staffDep = new JMenu("Standard Departement"); 
	private JMenu managDep = new JMenu("Management Departement");
	private JMenu checkincheckout = new JMenu("Global CheckIn-Out");
	private JMenu day = new JMenu("How is your day ?");
	private JMenu workers = new JMenu("Normal Workers");
	private JMenu todayState = new JMenu("Today CheckIn-Out");
	private JMenu csv = new JMenu("CSV");
	
	
	/**
	 * Attributs (3)
	 * @param listworkers JMenuItem
	 * @param listDep JMenuItem
	 * @param listOfMan JMenuItem
	 * @param listOfChecks JMenuItem
	 * @param howday JMenuItem
	 * @param today JMenuItem
	 * @param csvExport JMenuItem
	 * @param csvimport JMenuItem
	 */
	//Creation of the JMenuItem
	private JMenuItem listworkers = new JMenuItem("List of the workers");
	private JMenuItem listDep = new JMenuItem("List of departements");
	private JMenuItem listOfMan = new JMenuItem("Manager List");
	private JMenuItem listOfChecks = new JMenuItem("List CheckIn-Out");
	private JMenuItem howday = new JMenuItem("How is your day?");
	private JMenuItem today = new JMenuItem("Today List of CheckIn-Out");
	private JMenuItem csvExport = new JMenuItem("CSV Export");
	private JMenuItem csvimport = new JMenuItem("CSV Import");
	
	/**
	 * Constructor of the main view of the application
	 * Initialize all the elements needed in the main view
	 * @param model CompanyModel
	 * @throws Exception
	 */
	public Window(CompanyModel model) throws Exception
	{		
 
		this.modelComp = model;
		this.compCont = new CompanyController(modelComp);
		this.standardCont = new StandardDepartementController(modelComp); 
		this.managCont = new ManagementDepartementController(modelComp);
		this.managerCont = new ManagerController(modelComp); 
		this.workerCont = new NormalWorkerController(modelComp);
		
		//Initialization of the basic window 
		this.windowMaker();
		
		//Add the element into the main menu
		menuBar.add(csv);
		menuBar.add(staffDep);
		menuBar.add(managDep);
		menuBar.add(workers);
		menuBar.add(checkincheckout);
		menuBar.add(todayState);
		menuBar.add(day);
		
		//Main page of the application 
		JPanel mainPanel = new JPanel();
		JPanel container = new JPanel(); 
		JPanel container2 = new JPanel();
		JPanel container3 = new JPanel();
		
		JLabel hourlabel = new JLabel(); 
		JLabel bossLabel = new JLabel(); 
		JLabel companyLabel = new JLabel();
		
	    Font police = new Font("Serif", Font.PLAIN, 18);
	   
	    hourlabel.setFont(police);
	    hourlabel.setForeground(Color.black);
	    hourlabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    //update the date every seconds 
	    Horloge dateHour = new Horloge(); 
	    int delay = 1000; //milliseconds
	    
	    ActionListener taskPerformer = new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	dateHour.updateTime();
	        	hourlabel.setText("Date and Hour :" + "   " + dateHour.toString());
	       }
	    };
	    new Timer(delay, taskPerformer).start();
	    container.add(hourlabel, BorderLayout.NORTH);
	    
	    bossLabel.setFont(police);
	    bossLabel.setText("Boss : " + compCont.getbossfromcompany());
	    bossLabel.setForeground(Color.black);
	    bossLabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    companyLabel.setFont(police);
	    companyLabel.setText("Company Name : " + compCont.getNameOfCompanyController());
	    companyLabel.setForeground(Color.black);
	    companyLabel.setHorizontalAlignment(JLabel.CENTER);
	    
	    container2.add(bossLabel);
	    container3.add(companyLabel);
	    
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	    mainPanel.add(container3);
	    mainPanel.add(container2);
	    mainPanel.add(container);
	   
	    //add all the functions to the main view
		this.standardDepFunction();
		this.managementDepFunction();
		this.workersFunction();
		this.checkFunction();
		this.howIsYourDayFunction();
		this.dayStatusFunction();
		this.csvFunctions();
		
		//Add the panel to the window
		this.add(mainPanel);

		//Add of the JMenuBar 
		setJMenuBar(menuBar);
		this.setVisible(true);
	}

	/**
	 * WindowMaker 
	 * Function that can determine the caracteristics of the main view 
	 * Serialization is done when you're closing the window
	 */
	public void windowMaker()
	{
		//Define the title of the window
		this.setTitle("THE BEST COMPANY");
		
		//Define the object at the center of the window
		this.setLocationRelativeTo(null);
		
		//allow the resizable of the window
		this.setResizable(true);
		
		//set size 
		this.setSize(800,200);
		
		//end the process when we click on the red cross
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new WindowAdapter()
        {
            //serialization when you close the main window
			@Override
            public void windowClosing(WindowEvent e)
            {
        		Serialization.serialization(modelComp, "myData.ser");
    			e.getWindow().dispose();
            }
        });
		
		//Define the color of the background
		this.setBackground(Color.white);
	}
	
	/**
	 * Function that create a checkView window when called by the main view
	 * Add the JMenuItem to their respective JMenu
	 */
	public void checkFunction(){
		checkincheckout.add(listOfChecks);
		listOfChecks.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				ChecksView checkWindow = new ChecksView(modelComp); 
				
			}
		});
		
	}
	
	/**
	 * Function that create a StandardDepartementView window when called by the main view
	 * Add the JMenuItem to their respective JMenu
	 */
	public void standardDepFunction()
	{
		
		staffDep.add(listDep);
		listDep.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StandardDepartementView departementWindow = new StandardDepartementView(modelComp); 
				
			}
		});
	}
	
	/**
	 * Function that create a ManagementDepartementView window when called by the main view
	 * Add the JMenuItem to their respective JMenu
	 */
	public void managementDepFunction()
	{
		managDep.add(listOfMan);
		
		listOfMan.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				ManagementDepartementView ManagementDepwindow = new ManagementDepartementView(modelComp);
			}
			
		});
	}
	
	/**
	 * Function that create a HowIsYourDayView window when called by the main view
	 * Add the JMenuItem to their respective JMenu
	 */
	public void howIsYourDayFunction(){
		day.add(howday);
		
		howday.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				HowIsYourDayView howIsYourDayWindow = new HowIsYourDayView();
			}
			
		});
	}
	
	/**
	 * Function that create a NormalWorkerView window when called by the main view
	 * Add the JMenuItem to their respective JMenu
	 */
	public void workersFunction(){
		workers.add(listworkers);
		listworkers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				NormalWorkerView NormalWorkerWindowwindow = new NormalWorkerView(modelComp); 
				
			}
			
		});
	}
	
	/**
	 * Function that create a StateDayView window when called by the main view
	 * Add the JMenuItem to their respective JMenu
	 */
	public void dayStatusFunction()
	{
		todayState.add(today);
		today.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				StateDayView StateDaywindow = new StateDayView(modelComp); 
				
			}
			
		});
	}
	
	/**
	 * Function that call to type of function, either the exportation, either the importation
	 * Add the JMenuItem to their respective JMenu
	 */
	public void csvFunctions()
	{
		csv.add(csvExport);
		//call the exportation function by clicking on this JMenuItem
		csvExport.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					CSVExport.exportCSV(compCont);
					JOptionPane.showMessageDialog(null, "CSV exportation done", "Exportation status", JOptionPane.INFORMATION_MESSAGE);
					
				}
				catch (FileNotFoundException e)
				{
					e.printStackTrace();
				} 
			}
			
		});
		
		csv.add(csvimport);
		//call the importation function by clicking on this JMenuItem
		csvimport.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					CSVImport.importCSV(managerCont, workerCont, compCont);
					JOptionPane.showMessageDialog(null, "CSV importation done","Importation status", JOptionPane.INFORMATION_MESSAGE);
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				} 
			}
			
		});
	}

}