package techTest.pricers;

/**
 * Abstracts away pricing of an individual items
 * @author mmattek
 */
public abstract class Pricer {

	private String letter;
	public String getLetter() {
		return letter;
	}

	public void setLetter(String letter) {
		this.letter = letter;
	}


	public void setPricePerUnit(int pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	private int pricePerUnit;
	
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
		letter = letterVal;
		this.pricePerUnit = pricePerUnit;
		
	}
	

	public int getPricePerUnit(){
		return pricePerUnit;
	}
	
	public abstract int price( String orderString); 
}
