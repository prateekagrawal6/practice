package com.afkl.cases.pa.api;

import com.afkl.cases.pa.security.SecurityConfig;
import com.afkl.cases.pa.error.TravelCustomConstants;
import com.afkl.cases.pa.error.TravelCustomException;
import com.afkl.cases.pa.model.AirportResposeCode;
import com.afkl.cases.pa.model.Fare;
import com.afkl.cases.pa.model.FareDetails;
import com.afkl.cases.pa.model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class TravelRestAPIDataImpl {

	private static Logger log = LoggerFactory.getLogger(TravelRestAPIDataImpl.class);
	@Autowired
	private SecurityConfig travelConfig;

	@Autowired
	@Qualifier("oAuth2RestTemplate")
	private RestTemplate oAuth2RestTemplate;

	public FareDetails getFareData(String origin, String destination, String currency) throws TravelCustomException {
		log.info("starting getFareData call");
		FareDetails details = new FareDetails();
		Map<String, String> uriVariables = null;

		try {
			uriVariables = new HashMap<>();
			uriVariables.put(TravelCustomConstants.ORIGIN_PATH_PARAM, origin);
			uriVariables.put(TravelCustomConstants.DEST_PATH_PARAM, destination);

			UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(travelConfig.getFareUrl())
					.queryParam(TravelCustomConstants.CURRENCY_QUERY_PARAM, currency);

			ResponseEntity<Fare> fare = oAuth2RestTemplate.exchange(
					builder.buildAndExpand(uriVariables).toUri().toString(), HttpMethod.GET, null, Fare.class,
					uriVariables);

			if (fare.getStatusCode().value() == HttpStatus.OK.value()) {
				details.setFare(fare.getBody());
			} else {
				if (fare.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
					log.debug("getFareData  Data Not Found::");
					throw new TravelCustomException(TravelCustomConstants.NO_DATA_FOUND_E1003,
							fare.getBody().toString());
				} else if (fare.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
					log.debug("getFareData  BAD Request::");
					throw new TravelCustomException(TravelCustomConstants.MISSING_INPUT_ERROR_E1001,
							fare.getBody().toString());
				}
			}
		} catch (HttpClientErrorException he) {
			log.error("HttpClientErrorException in getFareData ::" + he.getLocalizedMessage());
			throw new RuntimeException(TravelCustomConstants.ERROR_GETTING_CONNECTION);
		} catch(IllegalArgumentException ie){
			log.error("Exception occured in getFareData IllegalArgumentException::" + ie.getLocalizedMessage());
			 throw new TravelCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 ie.getLocalizedMessage());
		}catch (Exception e) {
			log.error("Exception occured in getFareData ::" + e.getLocalizedMessage());
			throw new TravelCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 e.getLocalizedMessage());
		} finally {
			uriVariables = null;
		}
		log.info("End getFareData call");
		return details;
	}

	public Location getOrgDestDetails(String code) throws TravelCustomException {
		log.info("Start getOrgDestDetails call");
		try {
			return Optional.of(getCodes(code).getEmbedded().getLocations()
			.stream().filter(p -> p.getCode().equalsIgnoreCase(code))
			.map(p -> new Location(p.getCode(), p.getName(), p.getDescription()))
			.collect(Collectors.toList()).get(0)).orElseThrow(TravelCustomException::new);
								
		} catch (Exception e) {
			log.error("Exception occured in getOrgDestDetails ::" + e.getLocalizedMessage());
			throw new TravelCustomException(TravelCustomConstants.ERROR_GETTING_ORIGIN_DESTINATION,e.getLocalizedMessage());
		}
	}

	public AirportResposeCode getCodes(String code) throws TravelCustomException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(travelConfig.getSearchUrl())
				.queryParam(TravelCustomConstants.SEARCH_QUERY_PARAM, code);
		ResponseEntity<AirportResposeCode> locations = null;
		try {
			locations = oAuth2RestTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null,
					AirportResposeCode.class);
			
				if (locations.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
					log.debug("getCodes  Data Not Found::");
					throw new TravelCustomException(TravelCustomConstants.NO_DATA_FOUND_E1003,
							locations.getBody().toString());
				} else if (locations.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
					log.debug("getCodes  BAD Request::");
					throw new TravelCustomException(TravelCustomConstants.MISSING_INPUT_ERROR_E1001,
							locations.getBody().toString());
				}
			
		} catch (HttpClientErrorException he) {
			log.error("HttpClientErrorException in getFareData ::" + he.getLocalizedMessage());
			throw new RuntimeException(TravelCustomConstants.ERROR_GETTING_CONNECTION);
		} catch (Exception e) {
			log.error("Exception occured in getFareData ::" + e.getLocalizedMessage());
			 throw new TravelCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 e.getLocalizedMessage());
		} finally {
			builder = null;
		}
		return Optional.of(Optional.of(locations).orElseThrow(TravelCustomException::new).getBody())
		  						                 .orElseThrow(TravelCustomException::new);
		
	}
	
	public AirportResposeCode getAirports(Long pageNumber,Long size,String term) throws TravelCustomException {

		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(travelConfig.getAirportsUrl())
		.queryParam("page", pageNumber.toString())
		.queryParam("size", size.toString());
		if(null!=term && !term.equals("")){
			builder.queryParam("term", term);
		}
		
		ResponseEntity<AirportResposeCode> locations = null;
		try {
			locations = oAuth2RestTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.GET, null,
					AirportResposeCode.class);

			if (locations.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
					log.debug("getCodes  Data Not Found::");
					throw new TravelCustomException(TravelCustomConstants.NO_DATA_FOUND_E1003,
							locations.getBody().toString());
				} else if (locations.getStatusCode().value() == HttpStatus.BAD_REQUEST.value()) {
					log.debug("getCodes  BAD Request::");
					throw new TravelCustomException(TravelCustomConstants.MISSING_INPUT_ERROR_E1001,
							locations.getBody().toString());
				}
			
		} catch (HttpClientErrorException he) {
			log.error("HttpClientErrorException in getAirports ::" + he.getLocalizedMessage());
			throw new RuntimeException(TravelCustomConstants.ERROR_GETTING_CONNECTION);
		} catch (Exception e) {
			 throw new TravelCustomException(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
					 e.getLocalizedMessage());
		} finally {
			builder = null;
		}
		return Optional.of(Optional.of(locations).orElseThrow(TravelCustomException::new).getBody())
		  						                 .orElseThrow(TravelCustomException::new);
	}
}
