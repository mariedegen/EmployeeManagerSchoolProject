package mainApplicationViews;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * Class ChecksView
 * @author Marie DEGEN
 * @see CheckInCheckOutModel
 * @see CheckInCheckOutController
 */
public class ChecksView extends JFrame 
{
	/**
	 * @param serialVersionUID for the serialization
	 * @param model CompanyModel 
	 * @param compCont CompanyController
	 * @param workerCont NormalWorkerController
	 * @param data Object[][] of the JTable
	 * @param table JTable
	 * @param heading of the JTable
	 */
	private static final long serialVersionUID = 1L;
	private CompanyModel model;
	private CompanyController compCont; 
	private NormalWorkerController workerCont;
	private Object[][] data;
	private JTable table; 
	private String[] heading = {"Worker","Check-In", "Check-Out"};
	
	public ChecksView(CompanyModel model)
	{
		this.model = model;
		this.compCont = new CompanyController(model);
		this.workerCont = new NormalWorkerController(model);
		this.data = new Object[compCont.getListWorkerFromCompanyController().size()][3];
		this.table = new JTable(new DefaultTableModel(data, heading));
		
		//Define the title of the window
		this.setTitle("List of checkin-checkout");
		//Define the object at the center of the window
		this.setLocationRelativeTo(null);
		//allow the resizable of the window
		this.setResizable(true);
		//set size of the window
		this.setSize(720, 500);
		//Define the color of the background
		this.setBackground(Color.white);
		
		this.updateJTableElements(compCont.getListWorkerFromCompanyController());
		
		getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
		getContentPane().add(table, BorderLayout.CENTER);
		
		int delay = 1000; 
		//update the view every 5 secondes in case we have new check that arrived
		ActionListener updateView = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				updateJTableElements(compCont.getListWorkerFromCompanyController());
				getContentPane().add(table.getTableHeader(), BorderLayout.NORTH);
				getContentPane().add(table, BorderLayout.CENTER);
			}
			
		};
		
		new Timer(delay, updateView).start();
		
		this.setVisible(true);
	}
	
	/**
	 * Update JTable elements
	 * @param list the new list of normal worker
	 */
	public void updateJTableElements(List<NormalWorkerModel> list)
	{
		int i = 0;
		data = new Object[list.size()][3];

		for(NormalWorkerModel worker : list)
		{
			String name = worker.getName();
			String firstname = worker.getFirstName();
			List<Check> listchecks = workerCont.toStringCheckIn(name, firstname);
			List<Check> listchecksO = workerCont.toStringCheckOut(name, firstname);
			data[i] = new Object[]{worker.toString(), listchecks, listchecksO};
			i++;
		}
		
		TableModel tm = new DefaultTableModel(data, heading){
			private static final long serialVersionUID = 1L;
	
			@Override
		    public boolean isCellEditable(int rowIndex, int columnIndex) {
		        // This is how we disable editing:
		        return false;
	    }};
	    table.setAutoCreateRowSorter(true);
		table.setModel(tm);
	}

}
