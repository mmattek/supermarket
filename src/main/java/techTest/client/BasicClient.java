package techTest.client;

import java.io.*;
import java.net.*;

import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import techTest.pricers.StandardPricer;
import us.monoid.json.*;
import us.monoid.web.*;

public class BasicClient {

	private final URI urlRegStd;
	private final URI urlCheckout;
	
	public BasicClient() throws URISyntaxException {
		urlRegStd = new URI("http://localhost:8080/registerStandardPricer");
		urlCheckout  = new URI("http://localhost:8080/checkout");
	}

	public void regPricers() throws IOException, JSONException {
		regStd("A", 20);
		regStd("C", 30);
					
		
		
	}
	
	private void regStd(String letter, Integer unitPrice) throws JSONException{
		RestTemplate rt = new RestTemplate();
		
		StandardPricer post = rt.postForObject(urlRegStd, 
				new HttpEntity<StandardPricer>(
				new StandardPricer("A", 20))
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
			clnt.checkout("AA");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
