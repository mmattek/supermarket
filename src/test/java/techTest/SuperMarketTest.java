package techTest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SuperMarketTest {
	Market market;
	
	
	@Before
	public void setUp() throws Exception {
		market = new SuperMarket();
	}


	@Test
	public void test() {

		assertTrue(market.checkout("ABBACBBAB")==240);
				
	}

}
