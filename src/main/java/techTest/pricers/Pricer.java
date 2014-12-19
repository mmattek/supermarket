package techTest.pricers;

/**
 * Abstracts away pricing of an individual items
 * @author mmattek
 */
public abstract class Pricer {

	private final Character letter;
	private int pricePerUnit;
	
	// No args constructor is a no-no
	// but hide in the implementing classes
	public Pricer(){
		letter =null;
		pricePerUnit=0;
	}
	
	
	/**
	 * easier constructor to take String,
	 * Note if you happen to pass a string longer than a letter, takes the first
	 * @param letterVal
	 * @param pricePerUnit -- this is the standard, non bulk discounted price
	 */
	public Pricer(String letterVal, int pricePerUnit){
		this(letterVal.charAt(0), pricePerUnit);
		
	}
	
	public Pricer (Character letter, int pricePerUnit) {
		this.letter=letter;
		this.pricePerUnit=pricePerUnit;
	}

	public Character characterOfThisPricer() {
		return letter;
	}

	public String stringOfThisPricer() {
		return String.valueOf(letter);
	}

	public int getPricePerUnit(){
		return pricePerUnit;
	}
	
	public abstract int price( String orderString); 
}
