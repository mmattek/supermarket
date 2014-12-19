package techTest;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import techTest.pricers.ProductPricer;

public class SuperMarket implements Market {

	private static Map<Character, ProductPricer> pricers = 
			new ConcurrentHashMap<>();

	
	
	@Override
	public int checkout(String items) throws IllegalArgumentException{

		int total = 0;
		for (Character ch : getUniqueItemList(items)){
			ProductPricer productPricer = pricers.get(ch);
			if (productPricer==null){
				throw new IllegalArgumentException("can't find pricer for " + String.valueOf(ch));	
			}
			total = total + productPricer.price(items);
		}
		return total;
	}
 
	// filters down.. probably a more efficient algorithm here for very large orders?
	private Set<Character> getUniqueItemList(String items){
		Set<Character> set = new HashSet<Character>();
		for (Character ch : items.toCharArray()){
			set.add(ch);
		}
		return set;
	}

	@Override
	public void registerPricer(ProductPricer pricer) {
		pricers.put(pricer.characterOfThisPricer(), pricer);
		
	}

}
