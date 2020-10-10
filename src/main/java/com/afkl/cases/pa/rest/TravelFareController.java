package com.afkl.cases.pa.rest;

import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.FareDetails;

public interface TravelFareController {
	public FareDetails calculateFare(String origin,
									 String destination,
									 String currency) throws TravelCustomException;
	
}
