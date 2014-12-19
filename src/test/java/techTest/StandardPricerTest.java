package techTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import techTest.pricers.StandardPricer;

public class StandardPricerTest {
	Market market;

	@Test
	public void test() {
		market = new SuperMarket();
		market.registerPricer(new StandardPricer("A", 20));
		market.registerPricer(new StandardPricer("C", 30));
		assertTrue(market.checkout("AA")==40);
		assertTrue(market.checkout("CC")==60);	
		assertTrue(market.checkout("AC")==50);
	}

	/**
	 * check that a character unknown to the pricer throws proper
	 * exception
	 */
	@Test(expected=IllegalArgumentException.class)
	public void testBadArgs(){
		market = new SuperMarket();
		market.registerPricer(new StandardPricer("A", 20));
		market.registerPricer(new StandardPricer("C", 30));
		market.checkout("Z");
	}
}
