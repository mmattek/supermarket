package techTest.service;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import techTest.SuperMarket;
import techTest.pricers.*;

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

    
    @RequestMapping(value="/registerBulkPricer", method = RequestMethod.POST)
        public ResponseEntity<BulkDiscountPricer > regBulk(@RequestBody BulkDiscountPricer p){
        	SuperMarket.getSuperMarket().registerPricer(p);
        	return new ResponseEntity<BulkDiscountPricer >(p, HttpStatus.OK); 	
    }
    
    
    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@RequestBody String order) {
    	
    	return new ResponseEntity<Integer>(SuperMarket.getSuperMarket().checkout(order),
    			HttpStatus.OK);
    }
     
}