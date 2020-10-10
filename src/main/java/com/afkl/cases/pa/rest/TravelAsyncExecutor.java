package com.afkl.cases.pa.rest;

import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.FareDetails;
import com.afkl.cases.pa.model.Location;
import com.afkl.cases.pa.service.TravelFareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class TravelAsyncExecutor {

	@Autowired
	private TravelFareService fareService;
	
	public FareDetails callAsyncFare(String origin,
									 String destination,
									 String currency) throws TravelCustomException {
		
		FareDetails fareDetails =null;
		CompletableFuture<FareDetails> fareData = fareService.getFareDetails(origin, destination, currency);
		CompletableFuture<Location> originDetails = fareService.getOrgDestDetails(origin);
		CompletableFuture<Location> destinationDetails = fareService.getOrgDestDetails(destination);
		
		CompletableFuture.allOf(fareData,originDetails,destinationDetails);
		
		try {
			fareDetails = fareData.get();
			fareDetails.setOrigin(originDetails.get());
			fareDetails.setDestination(destinationDetails.get());
			
		}catch (InterruptedException | ExecutionException e) {
			throw new TravelCustomException(""+e, e.getLocalizedMessage());
			
		}	
		return fareDetails;
	
	}
}
