package techTest.pricers;

/**
 * Abstracts away pricing of an individual items
 * @author mmattek
 */
public interface Pricer {

	/**
	 * @param c -character to price
	 * @param numberOfItmes - the number Of Item to price
	 * @return
	 */
	public int price(Character c, int numberOfItems); 
}
