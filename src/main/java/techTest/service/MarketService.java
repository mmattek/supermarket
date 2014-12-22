package techTest.service;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import techTest.SuperMarket;
import techTest.pricers.*;

/**
 * Main REST enpoint of this service.
 * registerStandardPricer -- {"letter":"?", "pricePerUnit":"?"} -- sets letter price
 * registerBulkPricer --'{"letter":"?","pricePerUnit":"?","discountQuantity":"?","discountPrice":"?"}'
 * --sets a discounted X for $Y pricer
 * checkout -- Stringg "AAAABBB" etc, - gets price 
 * @author mitch
 *
 */
@RestController

public class MarketService {

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