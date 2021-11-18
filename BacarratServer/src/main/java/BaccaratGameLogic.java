import java.util.ArrayList;

public class BaccaratGameLogic {
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		int hand1ValTot = handTotal(hand1), hand2ValTot = handTotal(hand2);
		String winner = "";
		if(hand1ValTot > hand2ValTot) {
			winner = "Player";
		}
		else if(hand2ValTot > hand1ValTot) {
			winner = "Banker";
		}
		else if(hand1ValTot == hand2ValTot) {
			winner = "Draw";
		}
		return winner;
	}
	
	public static int handTotal(ArrayList<Card> hand) {
		int totalVal = 0;
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i).value >= 1 && hand.get(i).value <= 9)
			{
				totalVal += hand.get(i).value;
			}
			else {
				totalVal += 0;
			}
		}
		return totalVal;
	}
	
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		int bankerHand = hand.get(0).value + hand.get(1).value;
		if(bankerHand >= 7) {
			return false;
		}
		else if(bankerHand == 0 || bankerHand == 1 || bankerHand == 2) {
			return true;
		}
		else if(bankerHand == 3 || bankerHand == 4 || bankerHand == 5 || bankerHand == 6) {
			if(bankerHand == 6 && (playerCard.value == 6 || playerCard.value == 7)) {
				return true;
			}
			else if(bankerHand == 5 && ((playerCard.value >= 4 && playerCard.value <= 7) 
					|| evaluatePlayerDraw(hand) == false)) {
				return true;
			}
			else if(bankerHand == 4 && ((playerCard.value >= 2 && playerCard.value <= 7)
					|| (evaluatePlayerDraw(hand) == false))) {
				return true;
			}
			else if(bankerHand == 3 && (playerCard.value != 8 || evaluatePlayerDraw(hand) == false)) {
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		int playerHand = hand.get(0).value + hand.get(1).value;
		if(playerHand >= 6) {
			return false;
		}
		else if(playerHand <= 5) {
			return true;
		}
		return false;
	}
	
	public static int naturalWinner(ArrayList<Card> playerHand, ArrayList<Card> bankerHand) {
		if((handTotal(playerHand) == 8 || handTotal(playerHand) == 9) &&
			(handTotal(bankerHand) == 8 || handTotal(bankerHand) == 9)) {
			return 0;
		}
		else if(handTotal(playerHand) == 8 || handTotal(playerHand) == 9) {
			return 1;
		}
		else if(handTotal(bankerHand) == 8 || handTotal(bankerHand) == 9) {
			return 2;
		}
		else {
			return -1;
		}
	}
}
