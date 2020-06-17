package package1;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

/*
class MyThread implements Runnable
{
	public void run()
	{
		Server x =new Server();
		x.runServer();
	}
}
*/
public class Server implements Runnable
{
   private ObjectInputStream input;
   private ObjectOutputStream output;
   private static ServerSocket server;
   private Socket connection;
   private static Socket temp;
   private int counter = 1;
 
   	public void run()
   	{
   		runMyServer();
   	}
   
   public Server() {}
   
   public void runMyServer() {
		try // set up server to receive connections; process connections
	      {

	         while ( true ) 
	         {
	            try 
	            {
	               waitForConnection(); // wait for a connection
	               getStreams(); // get input & output streams
	               processConnection(); // process connection
	            } // end try
	            catch ( EOFException eofException ) 
	            {
	               displayMessage( "\nServer terminated connection" );
	            } // end catch
	            catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            finally 
	            {
	               closeConnection(); //  close connection
	               counter++;
	            } // end finally
	         } // end while
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      }
	}
   public void Init()
   {
	   try {
			server = new ServerSocket(12345,100);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
   }
   
   public void addConnection() throws ClassNotFoundException, IOException
   {
	   Server s = new Server();
	   s.connection = temp;
	   displayMessage("Connection:"+Thread.currentThread().getName()+" receive from:"+s.connection.getInetAddress().getHostAddress());
	   counter++;
	   s.getStreams();
	   s.processConnection(); 
   }
   /*
   public void runServer()
   {
	   try {
		   while(true)
		   {
			   displayMessage("\nWait for connection");
			   Socket temp = new Socket();
			   temp=server.accept();
			   if(temp!=null)
			   {
				   if(counter>1)
					   t.start();
				   else
				   {
					   connection = temp;
					   
					   displayMessage("Connection:"+counter+" receive from:"+connection.getInetAddress().getHostAddress());
					   counter++;
					   getStreams();
					   processConnection(); 
				   }
			   }
		   }
	   }catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
   */
   /*
   public void runServer() 
   {
		    try
		    {
		    	while(true)
		 	   {
				waitForConnection();
				getStreams();
				processConnection();  
		 	   }
			} 
		    catch (IOException e)
		    {
				e.printStackTrace();
			}
		    catch (ClassNotFoundException e) 
		    {
		    	e.printStackTrace();
		    }
		    finally 
            {
               closeConnection(); //  close connection
               counter++;
            } 
	   
   }
   */
   public void waitForConnection() throws IOException
   {
	   displayMessage("\nWait for connection");
	   connection = new Socket();
	   connection = server.accept();
	   counter++;
	   displayMessage("Connection:"+Thread.currentThread().getName()+" receive from:"+connection.getInetAddress().getHostAddress());
   }
   
   
   
   public void getStreams() throws IOException, ClassNotFoundException
   {
	   output=new ObjectOutputStream(connection.getOutputStream());
	   output.flush();
	   input=new ObjectInputStream(connection.getInputStream());
	   displayMessage("\nGot I/O Streams\n");
   }
   
   public void processConnection() throws ClassNotFoundException, IOException
   {
	   int count=0;
	   String message ="ClIENT>>> CONNECT";
	   System.out.println(message);
	   do{
		   try 
		   {
			   if(input.available()==0)
			   {
				   count++;
				   if(count==2)
					   break;
			   }
			   
			   message = (String)input.readObject();
			  
			   displayMessage("\nCLIENT>>> "+message);
			   count = 0;
		   } catch (ClassNotFoundException e) {
			   e.printStackTrace();
		   } catch (IOException e) {
			   e.printStackTrace();
		   }catch(Exception e) {
			   System.out.println("----------");
		   }
		   
	   }while(!message.equals( "CLIENT>>> TERMINATE" ));
	   displayMessage("\nClIENT>>> TERMINATE");
	   displayMessage("\nCONNECTION CLOSE");
   }
   
   public void closeConnection()
   {
	   displayMessage( "\nTerminating connection\n" );
	   
	   try {
		   if(output!=null)
			   output.close();
		   if(input!=null)
			   input.close();
		   if(connection!=null)
			   connection.close();
	   } catch (IOException e) {
		e.printStackTrace();
	   }
   }
   
   public void sendMessage(String message)
   {
	   try {
		output.writeObject(message);
		output.flush();
		displayMessage( "\nSERVER>>> " + message );
		
	} catch (IOException e) {
		displayMessage( "\nUnknown object type received" );
	}
   }
   
   private void displayMessage(String messgae)
   {
	   System.out.println(messgae);
   }
   public static void main(String []args) {
	   try {
			server = new ServerSocket(12345,100);
	   
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		   int count = 1;
		   while( count <= 10 ) {
			   Server tr = new Server();
			   Thread t = new Thread(tr , "线程" + count);
			   t.start();
			   count++;
		   }
	  }
   
} // end class Server