import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.application.Platform;
import javafx.scene.control.ListView;
/*
 * Clicker: A: I really get it    B: No idea what you are talking about
 * C: kind of following
 */

public class Server{

	int count = 1;	
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	TheServer server;
	private Consumer<Serializable> callback;
	BaccaratGame myGame = new BaccaratGame();
	BaccaratInfo gameInfo = new BaccaratInfo();
	String portNo;
	
	Server(Consumer<Serializable> call, String portNumber){
	
		callback = call;
		server = new TheServer();
		server.start();
		portNo = portNumber;
	}
	
	
	public class TheServer extends Thread{
		
		public void run() {
		
			try(ServerSocket mysocket = new ServerSocket(Integer.parseInt(portNo));){
		    System.out.println("Server is waiting for a client!");
		  
			
		    while(true) {
		
				ClientThread c = new ClientThread(mysocket.accept(), count);
				System.out.println("Client connected!");
				callback.accept("client has connected to server: " + "client #" + count);
				clients.add(c);
				c.start();
				
				count++;
				
			    }
			}//end of try
				catch(Exception e) {
					callback.accept("Server socket did not launch");
					e.printStackTrace();
				}
			}//end of while
		}
	

		class ClientThread extends Thread{
			
		
			Socket connection;
			int count;
			ObjectInputStream in;
			ObjectOutputStream out;
			
			ClientThread(Socket s, int count){
				this.connection = s;
				this.count = count;	
			}
			
			public void updateClients(BaccaratInfo gameInfo) {
				try {
					out.writeObject(gameInfo);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
//				//for(int i = 0; i < clients.size(); i++) {
//					if (count == clients.get.count) {
//					//
//					ClientThread t = clients.get(i);
//					try {
//					 t.out.writeObject(message);
//					}
//					catch(Exception e) {}
//					}
				//}
			}
			
			public void run(){
					
				try {
					in = new ObjectInputStream(connection.getInputStream());
					out = new ObjectOutputStream(connection.getOutputStream());
					connection.setTcpNoDelay(true);	
				}
				catch(Exception e) {
					System.out.println("Streams not open");
				}
				
				//updateClients("new client on server: client #"+count);
					
				// Read and BaccaratInfo object in while(true) with information needed for the game
				// Play the out in the server
				 while(true) {
					 //myGame.
					 //gameInfo = new BaccaratInfo();
					    try {
					    	//String data = in.readObject().toString();
					    	gameInfo = (BaccaratInfo)in.readObject();
					    	//myGame.playerHand();
					    	//String data = gameInfo.whoBetOn;
					    	//callback.accept(gameInfo);
					    	double winnings = myGame.evaluateWinnings();
					    	double betAmount = gameInfo.betAmount;
					    	int numClients = clients.size();
					    	callback.accept("Winnings " + winnings);
					    	callback.accept("Client #: " + clients);
					    	callback.accept("Bet Amount: "+ betAmount);
					    	updateClients(myGame.gameInfo);
					    	}
					    catch(Exception e) {
					    	callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					    	//updateClients("Client #"+count+" has left the server!");
					    	clients.remove(this);
					    	break;
					    }
					}
				}//end of run
			
			
		}//end of client thread
}