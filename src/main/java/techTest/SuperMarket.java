package techTest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SuperMarket implements Market {

	private static Map<Character, Integer> prices = 
			new ConcurrentHashMap<>(3);
	
/*	static {
		
		prices.put(new String("A").toCharArray()[0], 20);
		prices.put(new String("B").toCharArray()[0], 50);
		prices.put(new String("C").toCharArray()[0], 30);
	}*/
	
	@Override
	public int checkout(String items) {
		// TODO Auto-generated method stub
		return 0;
	}

}
