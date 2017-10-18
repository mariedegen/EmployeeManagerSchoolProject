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
import departements.StandardDepartementController;
import departements.StandardDepartementModel;

/**
 * Class StandardDepartementView
 * @author Marie DEGEN
 * @see StandardDepartementModel
 * @see StandardDepartementController
 */
public class StandardDepartementView extends JFrame 
{
	/**
	 * @param serialVersionUID for the serialization
	 * @param model CompanyModel 
	 * @param compCont CompanyController
	 * @param depCont StandardDepartementController
	 * @param data Object[][] of the JTable
	 * @param table JTable
	 * @param heading of the JTable
	 * @param name_field
	 * @param firstname_field
	 * @param departement_field
	 * @param newname_field
	 */
	private static final long serialVersionUID = 1L;
	private CompanyModel model;
	private CompanyController compCont; 
	private StandardDepartementController depCont; 
	private Object[][] data;
	private JTable table;
	private String[] heading = {"Departements", "Lead Manager"};
	private String name_field = "Manager Name";
	private String firstname_field = "Manager F.Name";
	private String departement_field = "Departement Name";
	private String newname_field = "New Departement Name";
	
	/**
	 * Constructor that include all the elements needed, initialization of the different elements
	 * @param model CompanyModel
	 */
	public StandardDepartementView(CompanyModel model)
	{
		this.model = model;
		this.compCont = new CompanyController(model);
		this.depCont = new StandardDepartementController(model);
		this.data = new Object[compCont.getListDepartementFromCompanyController().size()][2];
		this.table = new JTable(new DefaultTableModel(data, heading));
		
		//Define the title of the window
		this.setTitle("List of standard departements");
		//Define the object at the center of the window
		this.setLocationRelativeTo(null);
		//allow the resizable of the window
		this.setResizable(true);
		//set size of the window
		this.setSize(720, 500);
		//Define the color of the background
		this.setBackground(Color.white);
		
	   	this.updateDepList(compCont.getListDepartementFromCompanyController());
		getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
	    getContentPane().add(table, BorderLayout.CENTER);
	    
	    JButton addDep = new JButton("Add a departement");
	    JButton renameDep = new JButton("Rename a departement");
	    JButton deleteDep = new JButton("Delete a departement");
	    
	    JPanel south = new JPanel();
	    south.add(addDep);
	    south.add(renameDep);
	    south.add(deleteDep);
	    
	    addDep.addActionListener(new AddDepartementListener());
	    renameDep.addActionListener(new RenameDepartementListener());
	    deleteDep.addActionListener(new DeleteDepartementListener());
	    
	    this.add(south, BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	/**
	 * Add Departement Listener
	 * Ask the user to enter the different element needed to create a Departement 
	 * You can not have null informations entered or you must refill the form
	 * @exception if something went wrong
	 *
	 */
	class AddDepartementListener implements ActionListener
	  {
	    public void actionPerformed(ActionEvent arg0) 
	    {
		  JTextField nameDepartement = new JTextField(5);
		  JPanel myPanel = new JPanel();

		  myPanel.add(new JLabel(departement_field));
		  myPanel.add(nameDepartement);
		  myPanel.add(Box.createHorizontalStrut(15)); 
		  JTextField managerLead = new JTextField(5);

		  myPanel.add(new JLabel(name_field));
		  myPanel.add(managerLead);
		  myPanel.add(Box.createHorizontalStrut(15)); 
		  JTextField fman = new JTextField(5);

		  myPanel.add(new JLabel(firstname_field));
		  myPanel.add(fman);
		
		  int reply = JOptionPane.showConfirmDialog(null, myPanel, "Name of the new departement", JOptionPane.OK_CANCEL_OPTION);
		  if(reply == JOptionPane.OK_OPTION)
		  {
			try
			{
				String n = nameDepartement.getText();
				String m = managerLead.getText();
				String f = fman.getText();
				String mm = f + "." + m +"@thebest.fr";
				
				if(m.equals("") || f.equals("") || mm.equals("") || n.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
					JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
					
					n = nameDepartement.getText();
					m = managerLead.getText();
					f = fman.getText();
					mm = f + "." + m +"@thebest.fr";
				}
				
				compCont.addDepartementToCompanyController(depCont.createDepartement(n));
				depCont.setManagerLeadOfDepartement(n, m, f, mm);
				updateDepList(compCont.getListDepartementFromCompanyController());
				
				
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		  }
		}
	  }
	
	/**
	 * Rename Departement Listener 
	 * Ask the user all the information needed to rename a Departement 
	 * You can not have null text field entered or you must refill the form
	 */
	class RenameDepartementListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
		  
			JTextField name = new JTextField(5);
			JTextField newname = new JTextField(5);
			JPanel myPanel = new JPanel();
			
			myPanel.add(new JLabel(departement_field));
			myPanel.add(name);
			myPanel.add(Box.createHorizontalStrut(15)); 
			  
			myPanel.add(new JLabel(newname_field));
			myPanel.add(newname);
			
			int reply = JOptionPane.showConfirmDialog(null, myPanel, "Rename the departement", JOptionPane.OK_CANCEL_OPTION);
			if(reply == JOptionPane.OK_OPTION)
			{  
				try
				{
					String n = name.getText();
					String nn = newname.getText();
					if(n.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						n = name.getText();
						nn = newname.getText();
					}
	
					depCont.setName(n, nn);
					updateDepList(compCont.getListDepartementFromCompanyController());
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
	 }
	
	/**
	 * Delete Departement Listener 
	 * Ask the user the information needed to delete a Departement from the company
	 * Null text field are forbidden, or you must refill the form
	 */
	class DeleteDepartementListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			JTextField name = new JTextField(5);
			JPanel myPanel = new JPanel();
			
			myPanel.add(new JLabel(departement_field));
			myPanel.add(name);
			
			int reply = JOptionPane.showConfirmDialog(null, myPanel, "Delete a departement", JOptionPane.OK_CANCEL_OPTION);
			if(reply == JOptionPane.OK_OPTION)
			{ 
				try
				{
					String n = name.getText();
					if(n.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						n = name.getText();
					}
		
					compCont.deleteDepartementFromCompanyController(n);
					updateDepList(compCont.getListDepartementFromCompanyController());
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		
	 }
	
	/**
	 * Update the data from the JTable
	 * @param list the update list of departements
	 */
	public void updateDepList(List<StandardDepartementModel> list)
	{		
		int i = 0;
		String s;
		data = new Object[list.size()][2];
		for(StandardDepartementModel dep : list)
		{
			if(dep.getManagerOfDepartement() == null)
			{
				s = "No manager yet";
			}
			else 
			{
				s = dep.getManagerOfDepartement().toString();
			}
			data[i] = new Object[]{dep.getName(), s};
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
