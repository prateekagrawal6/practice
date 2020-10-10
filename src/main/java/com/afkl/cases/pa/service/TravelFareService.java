package com.afkl.cases.pa.service;

import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.FareDetails;
import com.afkl.cases.pa.model.Location;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface TravelFareService {
	
	public CompletableFuture<FareDetails> getFareDetails(String origin, String destination, String currency) throws TravelCustomException;
	public List<Location> getCodes(String code) throws TravelCustomException;
	public CompletableFuture<Location> getOrgDestDetails(String code)throws TravelCustomException;
		
}
