package mainApplicationViews;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * Class HowIsYourDay
 * @author Marie DEGEN
 */
public class HowIsYourDayView extends JFrame
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param dayMessage the title of the window
	 * @param pathNo the path the image if you answered NO
	 * @param pathYes the path the image if you answered YES
	 */
	private static final long serialVersionUID = 1L;
	private String dayMessage = "How is your day ?";
	private String pathYes = "C:\\Users\\Marie\\workspace\\JAVAProject\\10.png";
	private String pathNo = "C:\\Users\\Marie\\workspace\\JAVAProject\\cheer.gif";
	
	/**
	 * Constructor of the window with the different elements needed in it
	 */
	public HowIsYourDayView()
	{ 
		//Define the title of the window
		this.setTitle(dayMessage);

		this.setLocationRelativeTo(null);
		
		//allow the resizable of the window
		this.setResizable(true);
		
		this.setSize(750,500);
		
		ImageIcon image;
		JLabel label = new JLabel();
		
		//Asking the user if he's having a good day
		int reply = JOptionPane.showConfirmDialog(null, "Do you have a good day ?", "", JOptionPane.YES_NO_OPTION);
		
		//if yes he gets one image
		if(reply == JOptionPane.YES_OPTION)
		{
			image = new ImageIcon(pathYes);
			label = new JLabel("Perfect keep going ! ", image, JLabel.CENTER);
		}
		//if no he gets another image, cheering one
		else if (reply == JOptionPane.NO_OPTION)
		{
			image = new ImageIcon(pathNo);
			label = new JLabel("Cheer up you can do it ! ", image, JLabel.CENTER);
		}

		Font police = new Font("Courrier", Font.PLAIN, 24);
		label.setFont(police);
		
		JPanel pannel = new JPanel(new BorderLayout());
		pannel.add(label, BorderLayout.CENTER);
		
		this.add(pannel);
		this.setVisible(true);
	}
}
