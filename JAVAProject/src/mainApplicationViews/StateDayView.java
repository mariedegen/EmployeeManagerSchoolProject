package mainApplicationViews;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import checkIncheckOut.Check;
import company.CompanyController;
import company.CompanyModel;
import employees.NormalWorkerController;
import employees.NormalWorkerModel;

/**
 * Class StateDayView
 * @author Marie DEGEN
 * Can see the check in and check out for the current day
 */
public class StateDayView extends JFrame
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param model CompanyModel
	 * @param compCont CompanyController 
	 * @param workCont 	NormalWorkerController
	 * @param data Object[][] that will be put in the Jtable
	 * @param table JTable
	 * @param heading String[] of the JTable
	 */
	private static final long serialVersionUID = 1L;
	private CompanyModel model;
	private CompanyController compCont;
	private NormalWorkerController workCont;
	private Object[][] data;
	private JTable table;
	private String[] heading = {"Workers", "CheckIn", "CheckOut"};
	
	/**
	 * Constructor of the window
	 * @param model CompanyModel
	 * Create the window with all the elements needed in it
	 */
	public StateDayView(CompanyModel model)
	{
		this.model = model; 
		this.compCont = new CompanyController(model);
		this.workCont = new NormalWorkerController(model);
		this.data = new Object[compCont.getListWorkerFromCompanyController().size()][3];
		
		this.table = new JTable(new DefaultTableModel(data, heading));
		//Define the title of the window
		this.setTitle("State of the day");
		//Define the object at the center of the window
		this.setLocationRelativeTo(null);
		//allow the resizable of the window
		this.setResizable(true);
		//set size of the window
		this.setSize(720, 500);
		//Define the color of the background
		this.setBackground(Color.white);
		
		elementJTable(compCont.getListWorkerFromCompanyController());
		
		int delay = 1000; 
		//update the view every 5 secondes in case we have new check that arrived
		ActionListener updateView = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				elementJTable(compCont.getListWorkerFromCompanyController());
				getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
				getContentPane().add(table, BorderLayout.CENTER);
			}
			
		};
		
		new Timer(delay, updateView).start();
		
		getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		getContentPane().add(table, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	/**
	 * Pick all the elements needed that are going to be in the JTable
	 */
	public void elementJTable(List<NormalWorkerModel> list)
	{
		int i = 0;

		data = new Object[list.size()][3];
		
		for(NormalWorkerModel worker : list)
		{
			String name = worker.getName();
			String firstname = worker.getFirstName();
			List<Check> listchecks = workCont.toStringCheckIn(name,firstname);
			List<Check> listchecksO = workCont.toStringCheckOut(name,firstname);
			List<Check> listInToday = new ArrayList<Check>(); 
			List<Check> listOutToday = new ArrayList<Check>(); 
			
			//for the check in and out we only take the one for the current day and put it into a new list
			for(Check c : listchecks)
			{
				if(c.getDate().equals(LocalDate.now()))
				{
					listInToday.add(c);
				}
			}
			
			for(Check c : listchecksO)
			{
				if(c.getDate().equals(LocalDate.now()))
				{
					listOutToday.add(c);
				}
			}
			
			data[i] = new Object[]{worker.toString(), listInToday, listOutToday};
			i++;
		}
		//we create a table model with no cell editing
		TableModel tm = new DefaultTableModel(data, heading){
			private static final long serialVersionUID = 1L;
	
			@Override
		    public boolean isCellEditable(int rowIndex, int columnIndex) {
		        // This is how we disable editing
		        return false;
	    }};
	    table.setAutoCreateRowSorter(true);
	    table.setDefaultRenderer(Object.class, new ColorCellJTable());
		table.setModel(tm);
	}
}
