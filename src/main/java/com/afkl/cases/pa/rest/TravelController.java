package com.afkl.cases.pa.rest;

import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.AirportResponseList;
import com.afkl.cases.pa.model.Location;

import java.util.List;

public interface TravelController {
	
	public List<Location> populateOriginDest(String term, String lang) throws TravelCustomException;
	public AirportResponseList getAirports(String lang, Long pageNumber, Long size, String term, String sort) throws TravelCustomException;

}
