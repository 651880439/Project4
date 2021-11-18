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
		theDealer.generateDeck();
		theDealer.shuffleDeck();
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();
		if(BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1
			|| BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 2) {
			if(BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Player"
				&&	BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1) {
				totalWinnings += currentBet;
			}
			else if(BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Banker"
				&&	BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1) {
				totalWinnings -= currentBet;
			}
			else if(BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Draw"
				&&	BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1) {
				totalWinnings += currentBet*8;
			}
		}
		//BaccaratGameLogic.whoWon(playerHand, bankerHand);
		return totalWinnings;
	}
}
