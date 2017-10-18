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
import employees.NormalWorkerController;
import employees.NormalWorkerModel;

/**
 * Class NormalWorkerView 
 * @author Marie DEGEN
 * @see NormalWorkerController
 */
public class NormalWorkerView extends JFrame
{
	/**
	 * @param serialVersionUID for the serialization
	 * @param model CompanyModel 
	 * @param compCont CompanyController
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
	private NormalWorkerController workerCont;
	private Object[][] data;
	private JTable table;
	private String[] heading = {"Workers", "Departement"};
	private String name_field = "Name";
	private String firstname_field = "First Name";
	private String departement_field = "Departement Name";
	private String newname_field = "New Name";
	private String newfirstname_field = "New First Name";
	
	/**
	 * Constructor that include all the elements needed, initialization of the different elements
	 * @param model CompanyModel
	 */
	public NormalWorkerView(CompanyModel model)
	{
		this.model = model;
		this.compCont = new CompanyController(model);
		this.workerCont = new NormalWorkerController(model);
		this.data = new Object[compCont.getListDepartementFromCompanyController().size()][2];
		this.table = new JTable(new DefaultTableModel(data, heading));
		
		//Define the title of the window
		this.setTitle("List of workers");
		//Define the object at the center of the window
		this.setLocationRelativeTo(null);
		//allow the resizable of the window
		this.setResizable(true);
		//set size of the window
		this.setSize(720, 500);
		//Define the color of the background
		this.setBackground(Color.white);
		
		this.updateDataJTable(compCont.getListWorkerFromCompanyController());
		getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
	    getContentPane().add(table, BorderLayout.CENTER);
	    
	    JButton addWorker = new JButton("Add a worker");
	    JButton renameWorker = new JButton("Rename a worker");
	    JButton deleteWorker = new JButton("Delete a worker");
	    
	    JPanel southPanel = new JPanel();
	    southPanel.add(addWorker);
	    southPanel.add(renameWorker);
	    southPanel.add(deleteWorker);

	    
	    addWorker.addActionListener(new AddWorkerListener());
	    renameWorker.addActionListener(new RenameWorkerListener());
	    deleteWorker.addActionListener(new DeleteWorkerListener());
	    
	    this.add(southPanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}

	/**
	 * Add Worker Listener
	 * Ask the user to enter the different element needed to create a worker 
	 * You can not have null informations entered or you must refill the form
	 * @exception if something went wrong
	 *
	 */
	class AddWorkerListener implements ActionListener
	{
	    public void actionPerformed(ActionEvent arg0) 
	    {
			JTextField nameW = new JTextField(5);
			JTextField firstName = new JTextField(5);
			JTextField dep = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel(name_field));
			myPanel.add(nameW);
			myPanel.add(Box.createHorizontalStrut(15)); 
			  
			myPanel.add(new JLabel(firstname_field));
			myPanel.add(firstName);
			myPanel.add(Box.createHorizontalStrut(15));

			myPanel.add(new JLabel(departement_field));
			myPanel.add(dep);
			
			int reply = JOptionPane.showConfirmDialog(null, myPanel, "Informations of the new worker", JOptionPane.OK_CANCEL_OPTION);
			
			if(reply == JOptionPane.OK_OPTION)
			{
				try
				{
					String n = nameW.getText();
					String f = firstName.getText();
					String m = n + "." + f + "@thebest.fr";
					String d = dep.getText();
					
					if(m.equals("") || f.equals("") || d.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						n = nameW.getText();
						f = firstName.getText();
						m = n + "." + f + "@thebest.fr";
						d = dep.getText();

					}
					workerCont.createWorker(n, f, m,d);
					updateDataJTable(compCont.getListWorkerFromCompanyController());

					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	  }

	/**
	 * Rename Worker Listener 
	 * Ask the user all the information needed to rename a user (name, newname) 
	 * You can not have null text field entered or you must refill the form
	 */
	class RenameWorkerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JTextField nameWorker = new JTextField(5);
			JTextField fnameWorker = new JTextField(5);
			JTextField newName = new JTextField(5);
			JTextField newfirstName = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel(name_field));
			myPanel.add(nameWorker);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			myPanel.add(new JLabel(firstname_field));
			myPanel.add(fnameWorker);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			myPanel.add(new JLabel(newname_field));
			myPanel.add(newName);
			myPanel.add(Box.createHorizontalStrut(15));

			myPanel.add(new JLabel(newfirstname_field));
			myPanel.add(newfirstName);
			
			int reply = JOptionPane.showConfirmDialog(null, myPanel, "Informations of the worker", JOptionPane.OK_CANCEL_OPTION);
			
			if(reply == JOptionPane.OK_OPTION)
			{
				try
				{
					String nameW = nameWorker.getText();
					String fnnameW = fnameWorker.getText();
					String newnameW = newName.getText();
					String newfnameW = newfirstName.getText();
					String mailW = newnameW + "." + newfnameW + "@thebest.fr";
					
					if(mailW.equals("") || newnameW.equals("") || fnnameW.equals("") || newfnameW.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						nameW = nameWorker.getText();
						fnnameW = fnameWorker.getText();
						newnameW = newName.getText();
						newfnameW = newfirstName.getText();
						mailW = newnameW + "." + newfnameW + "@thebest.fr";
	
					}
					workerCont.renameWorker(nameW,fnnameW, newnameW, newfnameW, mailW);
					updateDataJTable(compCont.getListWorkerFromCompanyController());
					
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
			
		}
		
	}
	
	/**
	 * Delete Worker Listener 
	 * Ask the user the information needed to delete a worker from the company
	 * Null text field are forbidden, or you must refill the form
	 */
	class DeleteWorkerListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			JTextField nameWorker = new JTextField(5);
			JTextField fnameWorker = new JTextField(5);
			JPanel myPanel = new JPanel();
			myPanel.add(new JLabel(name_field));
			myPanel.add(nameWorker);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			myPanel.add(new JLabel(firstname_field));
			myPanel.add(fnameWorker);
			myPanel.add(Box.createHorizontalStrut(15)); 
			
			int reply = JOptionPane.showConfirmDialog(null, myPanel, "Informations of the worker", JOptionPane.OK_CANCEL_OPTION);
			if(reply == JOptionPane.OK_OPTION)
			{
				try
				{
					String n = nameWorker.getText();
					String fn = fnameWorker.getText();
	
					
					if(n.equals("") || fn.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Must have valid informations", "NULL TEXT FIELD", JOptionPane.ERROR_MESSAGE);
						JOptionPane.showConfirmDialog(null, myPanel, "Re-enter the information", JOptionPane.OK_CANCEL_OPTION);
						n = nameWorker.getText();
						fn = fnameWorker.getText();
					}
					
					compCont.deleteWorkerFromCompanyController(n,fn);
					updateDataJTable(compCont.getListWorkerFromCompanyController());
					
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
	 * @param list the update list of workers 
	 */
	public void updateDataJTable(List<NormalWorkerModel> list)
	{		
		int i = 0;
		String s;
		data = new Object[list.size()][2];
		for(NormalWorkerModel w : list)
		{
			if(w.getDepartement()== null)
			{
				s = "No Departement yet";
			}
			else 
			{
				s = w.getDepartement().toString();
			}
			data[i] = new Object[]{w.toString(), s};
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
