import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.ArrayList;
import java.util.function.Consumer;

import javafx.scene.control.TextField;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	TextField betAmount;
	//int bet = Integer.parseInt(betAmount);
	BaccaratInfo gameInfo;
	String IPAdd, portNo;
	//BaccaratGame myGame;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call, String IPAddress, String portNumber){
		callback = call;
		IPAdd = IPAddress;
		portNo = portNumber;
	}
	
	// Change object being read into BaccaratInfo Object
	public void run() {
		//gameInfo = new BaccaratInfo();
		try {
		socketClient= new Socket(IPAdd,Integer.parseInt(portNo));
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			try {
			gameInfo = (BaccaratInfo)in.readObject();
			//String message = in.readObject().toString();
			
			ArrayList<Card> playerHand = gameInfo.playerHand;
			ArrayList<Card> bankerHand = gameInfo.bankerHand;
			callback.accept(gameInfo);
			//callback.accept(playerHand);
			//callback.accept(bankerHand);
			//callback.accept(message);
			}
			catch(Exception e) {}
		}
	
    }
	
	public void send(BaccaratInfo gameInfo) {
		//BaccaratInfo gameInfo = new BaccaratInfo();
		try {
			out.writeObject(gameInfo);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
