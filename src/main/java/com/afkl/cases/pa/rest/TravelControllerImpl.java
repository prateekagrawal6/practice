package com.afkl.cases.pa.rest;

import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.AirportResponseList;
import com.afkl.cases.pa.model.Location;
import com.afkl.cases.pa.model.metrics.RestApiMetric;
import com.afkl.cases.pa.service.TravelFareService;
import com.afkl.cases.pa.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class TravelControllerImpl implements TravelController {

	@Autowired
	private TravelService travelService;

	@Autowired
	private TravelFareService travelFareService;

	@GetMapping("/search/code")
	public List<Location> populateOriginDest(@RequestParam("term") String term,
			@RequestParam(value = "lang", defaultValue = "en") String lang) throws TravelCustomException {

		return travelFareService.getCodes(term);
	}
	
	@GetMapping("/airports")
	public AirportResponseList getAirports(@RequestParam(value = "lang", defaultValue = "en") String lang,
			@RequestParam(value ="page",defaultValue="1") Long page,
			@RequestParam(value = "size",defaultValue = "10") Long size,
			@RequestParam(value ="term",defaultValue="") String term,
			@RequestParam(value ="sort",defaultValue="code") String sort) throws TravelCustomException {

		return travelService.getAirports(page,size,term,sort);
	}

	@GetMapping("/rest/metrics")
	public List<RestApiMetric>  getMetricsData() throws TravelCustomException {

		return travelService.metricsData();
	}
}