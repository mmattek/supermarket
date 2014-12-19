package techTest;

import techTest.pricers.ProductPricer;

public interface Market {
	
	public int checkout(String items) throws IllegalArgumentException;
	
	public void registerPricer(ProductPricer pricer) ;
	
}
