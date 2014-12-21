package techTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import techTest.pricers.BulkDiscountPricer;
import techTest.pricers.StandardPricer;

public class BulkDiscountPricerTest {
	private Market market  ;

	
	@Test
	public void test() {
		market = SuperMarket.getSuperMarket();
		market.registerPricer(new BulkDiscountPricer("B", 50, 5, 150));
		assertTrue(market.checkout("BBBBB")==150);
		// 6= $150 + 50 for the extra
		assertTrue(market.checkout("BBBBBB")==200);
		
		//2 * 150 (2 groups of 5) + 2 singles for $400 totalas
		assertTrue(market.checkout("BBBBBBBBBBBB")==400);
		
		
	}

}
