package techTest.service;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import techTest.SuperMarket;
import techTest.pricers.StandardPricer;

@RestController

public class MarketService {

    @RequestMapping("/")
    public String index() {
        return "Hello";
    }

   
    @RequestMapping(value="/registerStandardPricer", method = RequestMethod.POST)
    public ResponseEntity<StandardPricer> regStandard(@RequestBody StandardPricer p){
    	SuperMarket.getSuperMarket().registerPricer(p);
    	return new ResponseEntity<StandardPricer>(p, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@RequestBody String order) {
    	
    	return new ResponseEntity<Integer>(SuperMarket.getSuperMarket().checkout(order),
    			HttpStatus.OK);
    }
    
   
    /** 
     * simple total response object for service
     * so spring classes can wrap the response in JSON
     * these POJOS have to have a no-arg constructor and
     * getter/setters
     * @author mitch
     *
     */
    public class Total{
    	public Total(){}
    	
    	public Total(Integer i){
    		this.total =i ;
    	}
    	public Integer getTotal() {
			return total;
		}

		public void setTotal(Integer total) {
			this.total = total;
		}
		private Integer total;
    }
    
   
}