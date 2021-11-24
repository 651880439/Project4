
import java.util.HashMap;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class GuiClient extends Application{

	//Remove parts in relation to server
	
	TextField s1,s2,s3,s4, c1, IPAddress, portNumber, bidAmount;
	ComboBox<String> options;
	Button serverChoice,clientChoice,b1, connectToServer, send, exit, bid;
	HashMap<String, Scene> sceneMap;
	GridPane grid;
	HBox buttonBox;
	VBox clientBox;
	Scene startScene;
	BorderPane startPane;
	//Server serverConnection;
	Client clientConnection;
	BaccaratInfo gameInfo = new BaccaratInfo();
	
	ListView<String> listItems, listItems2;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Client side of the Program");
		
		this.connectToServer = new Button("Connect to the Server");
		this.connectToServer.setStyle("-fx-pref-width: 100px");
		this.connectToServer.setStyle("-fx-pref-height: 100px");
		
		this.exit = new Button("Exit");
		this.exit.setStyle("-fx-pref-width: 100px");
		this.exit.setStyle("-fx-pref-height: 100px");
		
		this.IPAddress = new TextField("Enter IP Address");
		this.IPAddress.setStyle("-fx-pref-width: 100px");
		this.IPAddress.setStyle("-fx-pref-height: 100px");
		
		this.portNumber = new TextField("Enter Port Number");
		this.portNumber.setStyle("-fx-pref-width: 100px");
		this.portNumber.setStyle("-fx-pref-height: 100px");
		
		this.exit.setOnAction(e->{ System.exit(0);
//				serverConnection = new Server(data -> {
//					Platform.runLater(()->{
//						listItems.getItems().add(data.toString());
//					});
//
//				});
											
		});
		
		this.connectToServer.setOnAction(e-> {primaryStage.setScene(displayBaccarat());
											primaryStage.setTitle("This is a client");
											clientConnection = new Client(data->{
							Platform.runLater(()->{listItems2.getItems().add(data.toString());
											});
							}, IPAddress.getText(), portNumber.getText());
							
											clientConnection.start();
		});
		
		this.clientBox = new VBox(100, IPAddress, portNumber, connectToServer, exit);
		startPane = new BorderPane();
		startPane.setPadding(new Insets(70));
		startPane.setCenter(clientBox);
		
		startScene = new Scene(startPane, 800,800);
		
		listItems = new ListView<String>();
		listItems2 = new ListView<String>();
		
		c1 = new TextField();
		b1 = new Button("Send");
		b1.setOnAction(e->{clientConnection.send(gameInfo);});
		
		sceneMap = new HashMap<String, Scene>();
		
		sceneMap.put("server",  createServerGui());
		sceneMap.put("client",  createClientGui());
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });
		
		 
		
		primaryStage.setScene(startScene);
		primaryStage.show();
		
	}
	
	public Scene displayBaccarat() {
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		
		this.send = new Button("Send");
		this.send.setStyle("-fx-pref-width: 100px");
		this.send.setStyle("-fx-pref-height: 100px");
		
		this.bid = new Button("bid");
		this.bid.setStyle("-fx-pref-width: 100px");
		this.bid.setStyle("-fx-pref-height: 100px");
		
		this.bidAmount = new TextField("Enter Bid Amount");
		this.bidAmount.setStyle("-fx-pref-width: 100px");
		this.bidAmount.setStyle("-fx-pref-height: 100px");
		
		this.options = new ComboBox<String>();
		this.options.setStyle("-fx-pref-width: 100px");
		this.options.setStyle("-fx-pref-height: 100px");
		options.getItems().add("Player");
		options.getItems().add("Banker");
		options.getItems().add("Draw");
		
		
		pane.setBottom(send);
		pane.setRight(exit);
		pane.setLeft(bid);
		pane.setTop(options);
		pane.setCenter(bidAmount);
		//pane.setCenter(listItems);
		this.bid.setOnAction(e->{gameInfo.betAmount = Integer.parseInt(bidAmount.getText());});
		this.send.setOnAction(e-> {clientConnection.send(gameInfo);});
		this.options.setOnAction(e->{gameInfo.whoBetOn = options.getAccessibleText();});
		return new Scene(pane, 500, 400);
	}
	
	public Scene createServerGui() {
		
		BorderPane pane = new BorderPane();
		pane.setPadding(new Insets(70));
		pane.setStyle("-fx-background-color: coral");
		pane.setCenter(listItems);
	
		return new Scene(pane, 500, 400);
		
		
	}
	
	public Scene createClientGui() {
		
		clientBox = new VBox(10, c1,b1,listItems2);
		clientBox.setStyle("-fx-background-color: blue");
		return new Scene(clientBox, 400, 300);
		
	}

}