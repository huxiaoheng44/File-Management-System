package package1;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Client extends JFrame 
{
   private static ObjectInputStream input;
   private static ObjectOutputStream output;
   private String message = ""; // message from server
   private String chatServer; // host server for this application
   private static Socket client;
   
   public Client(String host) {
		chatServer = host;
	}
   
   public void runClient(){
		try {
			connectToServer();
			getStreams();
			processConnection();
		}
		catch ( EOFException eofException ) 
	    {
	       displayMessage( "\nClient terminated connection" );
	    } // end catch
	    catch ( IOException ioException ) 
	    {
	       ioException.printStackTrace();
	    } // end catch
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    finally 
	    {
	       closeConnection(); // close connection
	    }
	}
   
   public void connectToServer() throws UnknownHostException, IOException
   {
	   displayMessage("\nAttempting  Connection");
	   client = new Socket(InetAddress.getByName(chatServer),12345);
	   displayMessage("Connection to:"+client.getInetAddress().getHostName());
   }
   
   public void getStreams() throws ClassNotFoundException
   {
	   try 
	   {
		output = new ObjectOutputStream( client.getOutputStream() );
		output.flush();
		input = new ObjectInputStream(client.getInputStream());
		displayMessage("\nGot I/O Stream\n");
	   } 
	   catch (IOException e) {
		displayMessage( "\nErroer in client get stream!" );
	}
   }
   
   public void processConnection()
   {
	   do
	   {
		   try 
		   {
			   message = (String)input.readObject();
			   displayMessage( "\nSERVER>>>" + message );
		   } catch (ClassNotFoundException | IOException e) {
			   displayMessage( "\nUnknown object type received" );
		}
	   }while(!message.equals( "SERVER>>> TERMINATE" ));
   }
   
   public static void sendMessage(String message)
   {
	   try 
	   {
		   output.writeObject(message);
		   output.flush();
		   displayMessage( "\nCLIENT>>> " + message);
	   } 
	   catch (IOException e) {
		System.out.println( "\nError writing object" );
	}
   }
   
   public static void closeConnection() {
		displayMessage( "\nClosing connection" );
		try 
	      {
			 sendMessage("TERMINATE");
	         output.close();
	         input.close();
	         client.close();
	      }
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      }
	}
   
   private static void displayMessage(final String messageToDisplay) {
		System.out.println(messageToDisplay);
	}
} // end class Client