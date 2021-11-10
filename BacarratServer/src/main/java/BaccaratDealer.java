import java.util.ArrayList;

public class BaccaratDealer {
	ArrayList<Card> deck = new ArrayList<Card>();
	
	public void generateDeck() {
		Card myCard;
		for(int i = 0; i < 13; i++) {
			myCard = new Card("Heart", i);
			deck.add(myCard);
		}
		for(int i = 0; i < 13; i++) {
			myCard = new Card("Spade", i);
			deck.add(myCard);
		}
		for(int i = 0; i < 13; i++) {
			myCard = new Card("Diamond", i);
			deck.add(myCard);
		}
		for(int i = 0; i < 13; i++) {
			myCard = new Card("Club", i);
			deck.add(myCard);
		}
	}
	
	public ArrayList<Card> dealHand(){
		ArrayList<Card> cardList = new ArrayList<Card>();
		return cardList;	
	}
	
	public Card drawOne() {
		Card myCard = new Card("", 0);
		return myCard;
	}
	
	public void shuffleDeck() {
		
	}
	
	public int deckSize() {
		return deck.size();
	}
}
