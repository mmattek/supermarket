package techTest;

import static org.junit.Assert.*;

import org.junit.Test;

import techTest.pricers.BulkDiscountPricer;
import techTest.pricers.StandardPricer;

/**
 * this is more "integrationish" overall test
 * 
 * @author mitch
 *
 */
public class SuperMarketTester {

	Market market;

	@Test
	public void test() {
		market = new SuperMarket();
		market.registerPricer(new StandardPricer("A", 20));
		market.registerPricer(new StandardPricer("C", 30));
		market.registerPricer(new BulkDiscountPricer("B", 50, 5, 150));
		assertTrue(market.checkout("AA") == 40);
		assertTrue(market.checkout("CC") == 60);
		assertTrue(market.checkout("AC") == 50);
		assertTrue(market.checkout("B") == 50);
		// 3* 20 + 5 @150 total w/discount + 1 @30 ==240
		assertTrue(market.checkout("ABBACBBAB") == 240);
		// order shouldn't matter
		assertTrue(market.checkout("BBBBBAAAC") == 240);

		// new item $25 or buy 6, get one free!
		market.registerPricer(new BulkDiscountPricer("D", 25, 7, 150));
		assertTrue(market.checkout("DDDDDDD") == 150);
		// one more , add $25
		assertTrue(market.checkout("DDDDDDDD") == 175);
		// 2 A, 13 B, 3 C, 15 D
		// 40 + 450 (2 batchs of 5 b's plus 3 odd)
		// + 90 + 325 (2 batchs of 7 Ds plus 1 odd) == 905
		String bigKahuna = "AA" + "BBBBB" + "BBBBB" + "BBB" + "CCC" + "DDDDDDD"
				+ "DDDDDDD" + "D";
		int bigKahunaValue = market.checkout(bigKahuna);
		assertTrue(bigKahunaValue == 905);
		// pass that? ship It!
	}

}
