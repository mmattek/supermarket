package techTest.pricers;

/**
 * Abstracts away pricing of an individual items
 * @author mmattek
 */
public interface ProductPricer {

	
	public Character characterOfThisPricer();
	
	public String stringOfThisPricer();
	
	/**
	 * @param theOrderString (entireOrder, in case entireContext is required
	 * its passed each time so a pricer for product can be reused
	 * across multiple orders 
	 * @param numberOfItmes - the number Of Item to price
	 * @return
	 */
	public int price( String orderString); 
}
