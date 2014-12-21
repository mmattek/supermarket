package techTest.client;

import java.io.*;
import java.net.*;

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
		StringWriter sw = new StringWriter(); 
		
		JSONObject o = new JSONObject("{'letter':'A', 'pricePerUnit':'20'}");
		 
		JSONResource json = new Resty().json(urlRegStd, Resty.content(o));
	
		System.out.println(json.toObject());
/*	Resty.content(someJson)	
		JSONResource json = new Resty().json(urlRegStd,
				form(data("letter", "A"), data("pricePerUnit", "20")));
*/
	}
	
	public void checkout(String order){
		
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
