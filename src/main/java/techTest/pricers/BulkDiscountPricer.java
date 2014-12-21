package techTest.pricers;

import org.apache.commons.lang3.StringUtils;

/**
 * BulkDiscountPricer gives "discountQuantity for discountPrice$" 
 * discounts for an item, give standard price for any quantity
 * less than y
 * @author mitch
 */
public class BulkDiscountPricer extends Pricer {
	private  int discountQuantity;
	private  int discountPrice;

	public BulkDiscountPricer(String letterVal, int pricePerUnit,
			int discountQuantity, int discountPrice){
		super(letterVal, pricePerUnit);
		this.discountPrice=discountPrice;
		this.discountQuantity=discountQuantity;
		
	}

	public void setDiscountQuantity(int discountQuantity) {
		this.discountQuantity = discountQuantity;
	}

	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}

	/**
	 * have to have a no-arg constructor for spring bean reflection to work properly
	 */
	public BulkDiscountPricer(){
		super();
		discountQuantity=1;
		discountPrice=1;
	}
	
	@Override
	public int price(String orderString) {
		int numberOfItems = StringUtils.countMatches(orderString, getLetter() );
		int total = (numberOfItems/discountQuantity) * discountPrice;
		// have to price the remainder that don't qualify for discount
		total = total + (numberOfItems%discountQuantity) *  getPricePerUnit();
		return total;
	}

	public int getDiscountQuantity() {
		return discountQuantity;
	}

	public int getDiscountPrice() {
		return discountPrice;
	}

}
