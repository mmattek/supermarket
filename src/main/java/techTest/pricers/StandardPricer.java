package techTest.pricers;

import org.apache.commons.lang3.StringUtils;

public class StandardPricer implements Pricer{

	private final Character letter;
	private int pricePerUnit;
	
	
	/**
	 * easier constructor to take String,
	 * Note if you happen to pass a string longer than a letter, takes the first
	 * @param letterVal
	 * @param pricePerUnit
	 */
	public StandardPricer(String letterVal, int pricePerUnit){
		this(letterVal.charAt(0), pricePerUnit);
		
	}
	
	public StandardPricer (Character letter, int pricePerUnit) {
		this.letter=letter;
		this.pricePerUnit=pricePerUnit;
	}

	@Override
	public Character characterOfThisPricer() {
		return letter;
	}

	@Override
	public String stringOfThisPricer() {
		return String.valueOf(letter);
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
		return numberOfItems * pricePerUnit;
	}
	
	
}
