package techTest;

import org.junit.Test;

import techTest.pricers.StandardPricer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapperTest {

	@Test
	public void test() throws JsonProcessingException {
		 ObjectMapper mapper = new ObjectMapper();
		 
		 StandardPricer p = new StandardPricer("A", 20);
		 System.out.println(mapper.writeValueAsString(p));
		 
		
	}

}
