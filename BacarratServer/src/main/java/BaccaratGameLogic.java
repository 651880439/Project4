import java.util.ArrayList;

public class BaccaratGameLogic {
	public static String whoWon(ArrayList<Card> hand1, ArrayList<Card> hand2) {
		
	}
	
	public static int handTotal(ArrayList<Card> hand) {
		return hand.size();
		
	}
	
	public static boolean evaluateBankerDraw(ArrayList<Card> hand, Card playerCard) {
		for(int i = 0; i < hand.size(); i++) {
			if(hand.get(i) == playerCard) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean evaluatePlayerDraw(ArrayList<Card> hand) {
		return false;
	}
}
