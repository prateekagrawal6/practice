package com.afkl.cases.pa.service;

import com.afkl.cases.pa.api.TravelRestAPIDataImpl;
import com.afkl.cases.pa.error.TravelCustomConstants;
import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.metrics.TravelMetricsHelper;
import com.afkl.cases.pa.model.AirportResponseList;
import com.afkl.cases.pa.model.AirportResposeCode;
import com.afkl.cases.pa.model.Location;
import com.afkl.cases.pa.model.metrics.RestApiMetric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TravelServiceImpl implements TravelService {

	private static Logger log = LoggerFactory.getLogger(TravelFareServiceImpl.class);
	
	@Autowired
    TravelRestAPIDataImpl restClient;
	
	@Override
	public List<RestApiMetric>  metricsData(){
		log.info("Inside metrics Data");
		return TravelMetricsHelper.metricDataResponse.getMetricsData().values().stream()
                .collect(Collectors.toList());
		
	}
		
	@Override
	public AirportResponseList getAirports(Long pageNumber,Long size,String term,String sortBy) throws TravelCustomException {
	
	return populateAirports(restClient.getAirports(pageNumber,size,term),sortBy); 
	}
	
	private AirportResponseList populateAirports(AirportResposeCode codes,String sort){
		
		AirportResponseList list = new AirportResponseList();
		list.setPage(codes.getPage());
		switch (sort) {
		case TravelCustomConstants.SORTBY_CODE:
		log.info("INSIDE CODE"+sort);
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getCode)).collect(Collectors.toList()));
			break;
		case TravelCustomConstants.SORTBY_NAME:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getName)).collect(Collectors.toList()));
			break;
		case TravelCustomConstants.SORTBY_DESC:
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getDescription)).collect(Collectors.toList()));
			break;
		default:
		log.info("DEFAULT SORT	"+sort);
			list.setLocations(codes.getEmbedded().getLocations().stream().sorted(Comparator.comparing(Location::getCode)).collect(Collectors.toList()));
			break;
		}
		
		
		return list;
	}

}
