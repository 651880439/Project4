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
	BaccaratInfo gameInfo;
	
	public double evaluateWinnings() {
		theDealer = new BaccaratDealer();
		playerHand = new ArrayList<Card>();
		bankerHand = new ArrayList<Card>();
		gameInfo = new BaccaratInfo();
		theDealer.generateDeck();
		theDealer.shuffleDeck();
		
		playerHand = theDealer.dealHand();
		bankerHand = theDealer.dealHand();
		gameInfo.playerHand = playerHand;
		gameInfo.bankerHand = bankerHand;
		gameInfo.betAmount = currentBet;
		if(BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1
			|| BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 2) {
			if(BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Player"
				&&	BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1) {
				totalWinnings += currentBet;
				gameInfo.betAmount = totalWinnings;
				gameInfo.whoBetOn = "Player";
			}
			else if(BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Banker"
				&&	BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1) {
				totalWinnings -= currentBet;
				gameInfo.betAmount = totalWinnings;
				gameInfo.whoBetOn = "Player";
			}
			else if(BaccaratGameLogic.whoWon(playerHand, bankerHand) == "Draw"
				&&	BaccaratGameLogic.naturalWinner(playerHand, bankerHand) == 1) {
				totalWinnings += currentBet*8;
				gameInfo.betAmount = totalWinnings;
				gameInfo.whoBetOn = "Draw";
			}
		}
		//BaccaratGameLogic.whoWon(playerHand, bankerHand);
		return totalWinnings;
	}
}
