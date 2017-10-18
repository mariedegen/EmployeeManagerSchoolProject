package timeTrackerClientNetwork;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Class TCPBuilderClient
 * @author Marie DEGEN
 * @see TCPClient
 * @see TCPRWMessage
 */

public class TCPBuilderClient implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param port 
	 * @param networkAddress you want to connect to
	 * @param s socket
	 * @param isA InetSocketAddress
	 */
	 private static final long serialVersionUID = 1L;
	 private int port = 8085;
	 private String networkAddress = "localhost";
	 Socket s;
	 InetSocketAddress isA;
	 
	 /**
	  * Constructor 
	  * The socket and the InetSocketAddress are initialize to null
	  */
	 public TCPBuilderClient()
	 {
		 s = null;
		 isA = null;
	 }
	 
	 /**
	  * Connection with the serveur
	  * @throws IOException if there are problems during the connexion
	  */
	 protected void setSocket() throws IOException 
	 {
		isA = new InetSocketAddress(networkAddress, port);
		s = new Socket(isA.getHostName(), isA.getPort());
		s.setSoTimeout(20000);
		s.getOutputStream().flush();
	 }
}

