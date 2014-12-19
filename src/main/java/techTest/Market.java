package techTest;

import techTest.pricers.Pricer;

public interface Market {
	
	public int checkout(String items) throws IllegalArgumentException;
	
	public void registerPricer(Pricer pricer) ;
	
}
