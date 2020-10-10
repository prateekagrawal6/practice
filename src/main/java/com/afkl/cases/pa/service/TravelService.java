package com.afkl.cases.pa.service;

import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.AirportResponseList;
import com.afkl.cases.pa.model.metrics.RestApiMetric;

import java.util.List;

public interface TravelService {
	public List<RestApiMetric>  metricsData()throws TravelCustomException;
	public AirportResponseList getAirports(Long pageNumber, Long size, String term, String sortBy) throws TravelCustomException;

}
