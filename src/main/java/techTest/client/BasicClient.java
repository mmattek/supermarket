package techTest.client;

import java.io.IOException;
import java.net.*;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import techTest.pricers.*;

public class BasicClient {

	private final URI urlRegStd;
	private final URI urlCheckout;
	private final URI urlRegBulk;
	
	public BasicClient() throws URISyntaxException {
		urlRegStd = new URI("http://localhost:8080/registerStandardPricer");
		urlRegBulk = new URI("http://localhost:8080/registerBulkPricer");
		urlCheckout  = new URI("http://localhost:8080/checkout");
	}

	public void regPricers() throws IOException {
		regStd("A", 20);
		regStd("C", 30);
		RestTemplate rt = new RestTemplate();
		// now just register the 5 "b"s for 150.
		BulkDiscountPricer post = rt.postForObject(urlRegBulk, 
				new HttpEntity<BulkDiscountPricer>(
						new BulkDiscountPricer("B", 50, 5, 150))
						, BulkDiscountPricer.class);
		System.out.println("reged pricer" + post);
		
	}
	
	private void regStd(String letter, Integer unitPrice){
		RestTemplate rt = new RestTemplate();
		
		StandardPricer post = rt.postForObject(urlRegStd, 
				new HttpEntity<StandardPricer>(
				new StandardPricer(letter, unitPrice))
				, StandardPricer.class);
		System.out.println("reged pricer" + post);
	
	}
	
	public void checkout(String order){
		RestTemplate rt = new RestTemplate();

		Integer response = rt.postForObject
				(urlCheckout, new HttpEntity<String>(order), Integer.class);
		System.out.println("YOUR TOTAL IS:" + response);
		System.out.println("THANKS FOR SHOPPING AT MITCH-MART!");
	}

	public static void main(String[] args) {
		BasicClient clnt;
		try {
			clnt = new BasicClient();
			clnt.regPricers();
			clnt.checkout("ABBACBBAB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
