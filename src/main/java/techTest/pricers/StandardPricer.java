package techTest.pricers;

import org.apache.commons.lang3.StringUtils;

public class StandardPricer extends Pricer{

	//we have to have a no arg constructor for bean reflection (possible to override with anotations)
	public StandardPricer(){
		super();
	}

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

		int numberOfItems = StringUtils.countMatches(orderString, getLetter() );
		return numberOfItems * getPricePerUnit();
	}
	
	
}
