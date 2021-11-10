import java.util.ArrayList;
import javafx.application.Application;

import javafx.scene.Scene;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BaccaratGame {
	ArrayList<Card> playerHand;
	ArrayList<Card> bankerHand;
	BaccaratDealer theDealer;
	double currentBet;
	double totalWinnings;
	
	public double evaluateWinnings() {
		return totalWinnings;
	}
}
