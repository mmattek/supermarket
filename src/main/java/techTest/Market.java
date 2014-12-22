package techTest;

import techTest.pricers.Pricer;

/**
 * primary interface of external facing market
 * @author mitch
 *
 */
public interface Market {
	
	public int checkout(String items) throws IllegalArgumentException;
	
	public void registerPricer(Pricer pricer) ;
	
}
