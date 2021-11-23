import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.function.Consumer;

import javafx.scene.control.TextField;



public class Client extends Thread{

	
	Socket socketClient;
	
	ObjectOutputStream out;
	ObjectInputStream in;
	TextField betAmount;
	//int bet = Integer.parseInt(betAmount);
	BaccaratInfo gameInfo;
	
	private Consumer<Serializable> callback;
	
	Client(Consumer<Serializable> call){
	
		callback = call;
	}
	
	// Change object being read into BaccaratInfo Object
	public void run() {
		//gameInfo = new BaccaratInfo();
		try {
		socketClient= new Socket("127.0.0.1",5555);
	    out = new ObjectOutputStream(socketClient.getOutputStream());
	    in = new ObjectInputStream(socketClient.getInputStream());
	    socketClient.setTcpNoDelay(true);
		}
		catch(Exception e) {}
		
		while(true) {
			try {
			gameInfo = (BaccaratInfo)in.readObject();
			//String message = in.readObject().toString();
			callback.accept(gameInfo);
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
