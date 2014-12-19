package techTest.pricers;

public class StandardPricer implements Pricer{

	private final Character letter;
	private int pricePerUnit;
	
	public StandardPricer (Character letter, int pricePerUnit) {
		this.letter=letter;
		this.pricePerUnit=pricePerUnit;
	}
	
	
	public int price(Character c, int numberOfItems) {
		
	}
	
	
}
