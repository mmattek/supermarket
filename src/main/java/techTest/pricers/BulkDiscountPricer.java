package techTest.pricers;

import org.apache.commons.lang3.StringUtils;

/**
 * BulkDiscountPricer gives "discountQuantity for discountPrice$" 
 * discounts for an item, give standard price for any quantity
 * less than y
 * @author mitch
 */
public class BulkDiscountPricer extends Pricer {
	private final int discountQuantity;
	private final int discountPrice;

	public BulkDiscountPricer(String letterVal, int pricePerUnit,
			int discountQuantity, int discountPrice){
		super(letterVal, pricePerUnit);
		this.discountPrice=discountPrice;
		this.discountQuantity=discountQuantity;
		
	}
	@Override
	public int price(String orderString) {
		int numberOfItems = StringUtils.countMatches(orderString, stringOfThisPricer() );
		int total = (numberOfItems/discountQuantity) * discountPrice;
		// have to price the remainder that don't qualify for discount
		total = total + (numberOfItems%discountQuantity) *  getPricePerUnit();
		return total;
	}

}
