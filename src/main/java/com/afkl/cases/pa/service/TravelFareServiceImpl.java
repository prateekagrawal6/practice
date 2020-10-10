package com.afkl.cases.pa.service;

import com.afkl.cases.pa.api.TravelRestAPIDataImpl;
import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.FareDetails;
import com.afkl.cases.pa.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class TravelFareServiceImpl implements TravelFareService {
	private static Logger log = LoggerFactory.getLogger(TravelFareServiceImpl.class);
	@Autowired
	TravelRestAPIDataImpl restClient;
	
	@Async("asyncExecutor")
	@Override
	public CompletableFuture<FareDetails> getFareDetails(String origin,String destination,String currency) throws TravelCustomException {
		log.info("getOrgDestDetails getFareData");
		return CompletableFuture.completedFuture(restClient.getFareData(origin, destination, currency));
	}
	


	
	@Override
	@Async("asyncExecutor")
	public CompletableFuture<Location> getOrgDestDetails(String code)throws TravelCustomException {
		log.info("getOrgDestDetails started"+code);
		return CompletableFuture.completedFuture(restClient.getOrgDestDetails(code));
	}
	
	@Override
	public List<Location> getCodes(String code) throws TravelCustomException {
		
	return restClient.getCodes(code).getEmbedded().getLocations().
				stream().
				map(p -> new Location(p.getCode(), p.getName(), p.getDescription()))
		        .collect(Collectors.toList());
	}

		
	
}
