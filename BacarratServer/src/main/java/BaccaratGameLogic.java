import java.util.ArrayList;

public class BaccaratGameLogic {
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		
	}
	
	public static int handTotal(ArrayList<Card> hand) {
		return hand.size();
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
}
