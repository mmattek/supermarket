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

    @RequestMapping(value = "/checkout", method = RequestMethod.POST)
    public ResponseEntity<Integer> update(@RequestBody String total) {
    	
    	return new ResponseEntity<Integer>(12, HttpStatus.OK);
    }
    
    
   
}