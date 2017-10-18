package mainApplicationViews;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import company.CompanyController;
import company.CompanyModel;
import employees.ManagerController;
import employees.ManagerModel;
import employees.NormalWorkerController;

/**
 * Class ManagementDepartementView
 * @author Marie DEGEN
 * @see ManagementDepartementController
 *
 */
public class ManagementDepartementView extends JFrame
{
	/**
	 * @param serialVersionUID for the serialization
	 * @param model CompanyModel 
	 * @param compCont CompanyController
	 * @param managCont ManagerController
	 * @param workerCont NormalWorkerController
	 * @param data Object[][] of the JTable
	 * @param table JTable
	 * @param heading of the JTable
	 * @param name_field
	 * @param firstname_field
	 * @param departement_field
	 * @param newname_field
	 * @param newfirstname_field
	 *
	 */
	private static final long serialVersionUID = 1L;
	private CompanyModel model;
	private CompanyController compCont; 
	private ManagerController managCont; 
	private NormalWorkerController workerCont; 
	private Object[][] data;
	private JTable table; 
	private String[] heading = {"Manager", "Departements", "Lead"};
	private String name_field = "Name";
	private String firstname_field = "First Name";
	private String departement_field = "Departement Name";
	private String newname_field = "New Name";
	private String newfirstname_field = "New First Name";
	
	/**
	 * Constructor that include all the elements needed, initialization of the different elements
	 * @param model CompanyModel
	 */	
	public ManagementDepartementView(CompanyModel model)
	{
		this.model = model;
		this.compCont = new CompanyController(model);
		this.managCont = new ManagerController(model);
		this.workerCont = new NormalWorkerController(model);
		this.table = new JTable(new DefaultTableModel(data, heading));
		
		//Define the title of the window
		this.setTitle("List of managers");
		//Define the object at the center of the window
		this.setLocationRelativeTo(null);
		//allow the resizable of the window
		this.setResizable(true);
		//set size of the window
		this.setSize(720, 500);
		//Define the color of the background
		this.setBackground(Color.white);
		
		this.updateJTableElements(compCont.getListManagerFromCompanyController());
	    
	    JButton addManager = new JButton("Add a manager");
	    JButton renameManager = new JButton("Rename a manager");
	    JButton deleteManager = new JButton("Delete a manager");
	    
	    JPanel south = new JPanel();
	    south.add(addManager);
	    south.add(renameManager);
	    south.add(deleteManager);
	    
	    addManager.addActionListener(new AddManagerListener());
	    renameManager.addActionListener(new RenameManagerListener());
	    deleteManager.addActionListener(new DeleteManagerListener());
	    
		getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
	    getContentPane().add(table, BorderLayout.CENTER);
	    this.add(south, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	/**
	 * Add Manager Listener
	 * Ask the user to enter the different element needed to create a manager 
	 * You can not have null informations entered or you must refill the form
	 * @exception if something went wrong
	 *
	 */
	class AddManagerListener implements ActionListener
	  {
	    public void actionPerformed(ActionEvent arg0) 
	    {
			JTextField nameManager = new JTextField(5);
			JTextField firstName = new JTextField(5);
			JTextField dep = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel(name_field));
			myPanel.add(nameManager);
			myPanel.add(Box.createHorizontalStrut(15)); 
			  
			myPanel.add(new JLabel(firstname_field));
			myPanel.add(firstName);
			myPanel.add(Box.createHorizontalStrut(15));

			myPanel.add(new JLabel(departement_field));
			myPanel.add(dep);
			
			int reply1 = JOptionPane.showConfirmDialog(null, myPanel, "Informations of the new manager", JOptionPane.OK_CANCEL_OPTION);
			if(reply1 == JOptionPane.OK_OPTION)
			{
				try
				{
					String n = nameManager.getText();
					String f = firstName.getText();
					String m = f + "." + n + "@thebest.fr";
					String d = dep.getText();
					
					if(m.equals("") || f.equals("") || d.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						n = nameManager.getText();
						f = firstName.getText();
						m = f + "." + n + "@thebest.fr";
						d = dep.getText();
					}
					
					ManagerModel manag =  managCont.createManager(n, f, m, compCont.getManagementFromCompanyController());
					compCont.addManagerToCompanyController(manag);
					workerCont.setDepartement(n, f, m, d);
					
					//ask the user if the manager is in lead of the departement or not
					int reply = JOptionPane.showConfirmDialog(null, "Manager Lead", "Is he in lead of the departement ?", JOptionPane.YES_NO_OPTION);
					if(reply == JOptionPane.YES_OPTION)
					{
						managCont.setLead(true, manag);
						manag.getDepartement().setManagerLeadOfDepartement(manag);
					}
					else
					{
						managCont.setLead(false, manag);
					}
					
					updateJTableElements(compCont.getListManagerFromCompanyController());
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	  }

	/**
	 * Rename Manager Listener
	 * Ask the user to enter the different element needed to rename a manager 
	 * You can not have null informations entered or you must refill the form
	 * @exception if something went wrong
	 *
	 */
	class RenameManagerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JTextField nameManager = new JTextField(5);
			JTextField fnameManager = new JTextField(5);
			JTextField newName = new JTextField(5);
			JTextField newfirstName = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel(name_field));
			myPanel.add(nameManager);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			myPanel.add(new JLabel(firstname_field));
			myPanel.add(fnameManager);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			myPanel.add(new JLabel(newname_field));
			myPanel.add(newName);
			myPanel.add(Box.createHorizontalStrut(15));

			myPanel.add(new JLabel(newfirstname_field));
			myPanel.add(newfirstName);
			
			int reply = JOptionPane.showConfirmDialog(null, myPanel, "Informations of the manager", JOptionPane.OK_CANCEL_OPTION);
			if(reply == JOptionPane.OK_OPTION)
			{
				try
				{
					String n = nameManager.getText();
					String fn = fnameManager.getText();
					String f = newName.getText();
					String d = newfirstName.getText();
					String m = d + "." + f + "@thebest.fr";
					
					if(m.equals("") || f.equals("") || fn.equals("") || d.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						
						n = nameManager.getText();
						fn = fnameManager.getText();
						f = newName.getText();
						d = newfirstName.getText();
						m = d + "." + f + "@thebest.fr";
					}
					
					managCont.renameManager(n,fn, f, d, m);
					updateJTableElements(compCont.getListManagerFromCompanyController());
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		
	}
	
	/**
	 * Delete Manager Listener
	 * Ask the user to enter the different element needed to delete a manager 
	 * You can not have null informations entered or you must refill the form
	 * @exception if something went wrong
	 *
	 */
	class DeleteManagerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JTextField nameManager = new JTextField(5);
			JTextField fnameManager = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel(name_field));
			myPanel.add(nameManager);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			myPanel.add(new JLabel(firstname_field));
			myPanel.add(fnameManager);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			int reply = JOptionPane.showConfirmDialog(null, myPanel, "Informations of the manager", JOptionPane.OK_CANCEL_OPTION);
			if(reply == JOptionPane.OK_OPTION)
			{
				try
				{
					String n = nameManager.getText();
					String fn = fnameManager.getText();
	
					
					if(n.equals("") || fn.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						
						n = nameManager.getText();
						fn = fnameManager.getText();
					}
					
					compCont.deleteManagerFromCompanyController(n,fn);
					updateJTableElements(compCont.getListManagerFromCompanyController());
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
			
	}
		
	/**
	 * Update the data from the JTable
	 * @param list the update list of managers 
	 */
	public void updateJTableElements(List<ManagerModel> list)
	{		
		int i = 0;
		String managerString;
		String lead; 
		data = new Object[list.size()][2];
		for(ManagerModel man : list)
		{
			if(man.getDepartement() == null)
			{
				managerString = "No Departement yet";
			}
			else
			{
				managerString = man.getDepartement().toString();
			}
			
			if(managCont.isLead(man))
			{
				lead = "Yes";
			}
			else
			{
				lead = "No";
			}
			
			data[i] = new Object[]{man.toString(), managerString, lead};
			i++;
		}
		
		TableModel tm = new DefaultTableModel(data, heading){
			private static final long serialVersionUID = 1L;
	
			@Override
		    public boolean isCellEditable(int rowIndex, int columnIndex) {
		        // This is how we disable editing:
		        return false;
	    }};;
	    table.setAutoCreateRowSorter(true);
		table.setModel(tm);
	}

}
