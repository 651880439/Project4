import java.util.ArrayList;
import java.util.*;

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
		Card myCard1, myCard2;
		myCard1 = deck.get(0);
		myCard2 = deck.get(1);
		deck.remove(0);
		deck.remove(1);
		cardList.add(myCard1);
		cardList.add(myCard2);
		return cardList;	
	}
	
	public Card drawOne() {
		Card myCard = new Card("", 0);
		myCard = deck.get(0);
		deck.remove(0);
		return myCard;
	}
	
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}
	
	public int deckSize() {
		return deck.size();
	}
}
