package timeTracker;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

import javax.swing.*;
import javax.swing.Timer;

import employees.NormalWorkerModel;
import horloge.Horloge;
import timeTrackerClientNetwork.TCPClient;

/**
 * Class TimeTrackerView
 * @author Marie DEGEN
 * @see TimeTrackerController
 * @see TimeTrackerModel
 */
public class TimeTrackerView extends JFrame 
{
	/**
	 * Attributs
	 * @param synchroButton JButton
	 * @param checkButton JButton
	 * @param mainContainer JPanel 
	 * @param container JPanel
	 * @param label JLabel
	 * @param model TimeTrackerModel
	 * @param timetrackerCont TimeTrackerController
	 * @param comboWorkers JComboBox<NormalWorker>
	 * @param titleWindow the title of the main window
	 * @param serialVersionUID for the serialization
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton synchroButton = new JButton("Synchronization with main app."); 
	private JButton checkButton = new JButton("Check in/out");
	
	private JPanel mainContainer = new JPanel();
	private JPanel container = new JPanel(); 
	
	private JLabel label = new JLabel();
	
	private TimeTrackerModel model; 
	private TimeTrackerController timetrackerCont; 
	
	private JComboBox<NormalWorkerModel> comboWorkers = new JComboBox<NormalWorkerModel>(); 
	
	private String titleWindow = "Time Tracker emulator";
	
	/**
	 * Constructor 
	 * @param model TimeTrackerModel
	 * @param client TimeTrackerController
	 * Initialize the attributs and initialize the window with the main elements in it
	 */
	public TimeTrackerView(TimeTrackerModel model, TCPClient client)
	{		
		this.model = model; 
		this.timetrackerCont = new TimeTrackerController(model, this, client); 

		//Define the title of the window
		this.setTitle(titleWindow);
		//Define the size of the window
		this.setSize(500,500);
		//Define the object at the center of the window
		this.setLocationRelativeTo(null);
		//allow the resizable of the window
		this.setResizable(true);
		//end the process when we click on the red cross
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Define the color of the background
		this.setBackground(Color.WHITE);
		
	    mainContainer.setBackground(Color.white);
	    mainContainer.setLayout(new BorderLayout());

	    //Creation of a new panel where we add the two buttons "synchro" and "check"
	    JPanel south = new JPanel();
	    south.add(synchroButton);
	    south.add(checkButton);
	    mainContainer.add(south, BorderLayout.SOUTH);
	    
	    //We can use anonymes classes 
	    //Functions for the buttons we have 
	    synchroButton.addActionListener(new SynchroButtonListener());
	    checkButton.addActionListener(new CheckButtonListener());
	    
	    //Creation of a text zone
	    Font police = new Font("Courrier", Font.BOLD, 16);
	    label.setFont(police);
	    label.setForeground(Color.black);
	    label.setHorizontalAlignment(JLabel.CENTER);
	    
	    //Creation of the hour and date in the up side of the container
	    Horloge horloge = new Horloge(); 
	    
	    int delay = 1000; //milliseconds
	    //update of the time every seconde
	    ActionListener taskPerformer = new ActionListener() {
	        public void actionPerformed(ActionEvent evt) {
	        	horloge.updateTime();
	        	label.setText("Date and Hour :" + "   " + horloge.toString());
	        }
	    };
	    new Timer(delay, taskPerformer).start();
	    mainContainer.add(label, BorderLayout.NORTH);
    
	    //Creation of the drop down menu 
	    comboWorkers.setPreferredSize(new Dimension(300,30));
	    container.add(comboWorkers);
	    mainContainer.add(container, BorderLayout.CENTER);
	    
	    this.setContentPane(mainContainer);
	    this.setVisible(true);
	}	 
	
	/**
	 * Synchronization Button Listener
	 * If you click on it the list of workers is synchronized with the server
	 *
	 */
	public class SynchroButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0) 
		{
			 timetrackerCont.synchronize();       
		}
	}
	
	/**
	 * Check Button Listener, for the user to send a check to the server
	 */
	class CheckButtonListener implements ActionListener
	{
	   public void actionPerformed(ActionEvent e) 
	   {
	    	timetrackerCont.check((NormalWorkerModel)comboWorkers.getSelectedItem());
	   }
	}
	
	/**
	 * Update the list of workers 
	 * @param listworker the new list
	 */
	public void updateListWorker(List<NormalWorkerModel> listworker)
	{
	  comboWorkers.removeAllItems();
	  for(NormalWorkerModel worker : listworker)
	  {
		  comboWorkers.addItem(worker);
	  }
	}
}