package techTest.pricers;

import org.apache.commons.lang3.StringUtils;

public class StandardPricer extends Pricer{


	public StandardPricer(String string, int pricePerUnit) {
		super(string, pricePerUnit);
	}

	/**
	 * The orderString is passed here so this pricer can be used multiple
	 * times by passing the context each time
	 * @param orderString
	 * @return
	 */
	@Override
	public int price(String orderString) {

		int numberOfItems = StringUtils.countMatches(orderString, stringOfThisPricer() );
		return numberOfItems * getPricePerUnit();
	}
	
	
}
