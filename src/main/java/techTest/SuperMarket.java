package techTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import techTest.pricers.Pricer;

public class SuperMarket implements Market {

	private static Map<String, Pricer> pricers = 
			new HashMap<>();

	private static SuperMarket superMarket = new SuperMarket();
	
	//singleton patern, only access thru getSuperMarket()
	private SuperMarket(){}
	
	public static SuperMarket getSuperMarket(){
		return superMarket;
	}

	
	@Override
	public int checkout(String items) throws IllegalArgumentException{

		int total = 0;
		for (Character ch : getUniqueItemList(items)){
			Pricer productPricer = pricers.get(ch);
			if (productPricer==null){
				throw new IllegalArgumentException("can't find pricer for " + String.valueOf(ch));	
			}
			total = total + productPricer.price(items);
		}
		return total;
	}
 
	// filters down uniq chars.. probably a more efficient algorithm here for very VERY large orders?
	private Set<Character> getUniqueItemList(String items){
		Set<Character> set = new HashSet<Character>();
		for (Character ch : items.toCharArray()){
			set.add(ch);
		}
		return set;
	}

	@Override
	public void registerPricer(Pricer pricer) {
		pricers.put(pricer.getLetter(), pricer);
		
	}

}
