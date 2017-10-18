package mainApplicationNetwork;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Class TCPBuilderServer
 * @author Marie DEGEN
 * @see TCPServer
 * @see TCPRWMessage
 */
public class TCPBuilderServer implements Serializable
{
	/**
	 * Attributs
	 * @param serialVersionUID for the serialization
	 * @param port 
	 * @param networkAddress you want to connect to
	 * @param s socket
	 * @param isA InetSocketAddress
	 * @param ss ServerSocket
	 */
	 private static final long serialVersionUID = 1L;
	 ServerSocket ss; 
	 Socket s; 
	 InetSocketAddress isA;
	 private int port = 8085;
	 private String networkAddress = "localhost";
	 
	 /**
	  * Constructor the ServerSocket and the InetSocketAddress are initialized
	  */
	 TCPBuilderServer()
	 {
		 ss = null;
		 isA = null;
	 }
	  /**
	   * Connexion withe the client
	   * @throws IOException if there are problems durint the connexion
	   */
	 protected void setSocket() throws IOException 
	 {
		isA = new InetSocketAddress(networkAddress, port);
		ss = new ServerSocket(isA.getPort());
	 }
}