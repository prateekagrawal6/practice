package com.afkl.cases.pa.rest;

import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.FareDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class TravelFareControllerImpl implements TravelFareController {

	@Autowired
	private TravelAsyncExecutor airportAsynch;


	@Override
	@GetMapping("fares/{origin}/{destination}")
	public FareDetails calculateFare(@PathVariable("origin") String origin,
			@PathVariable("destination") String destination,
			@RequestParam(value = "currency", defaultValue = "EUR") String currency) throws TravelCustomException {
		
		return airportAsynch.callAsyncFare(origin, destination, currency);
	}
}
