import java.io.Serializable;
import java.util.ArrayList;

//Here we have the file that implements classobeject parameter. 
public class BaccaratInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double betAmount;
	public String whoBetOn;
	public ArrayList<Card> playerHand;
	public ArrayList<Card> bankerHand;
}
